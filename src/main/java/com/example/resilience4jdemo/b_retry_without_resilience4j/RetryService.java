package com.example.resilience4jdemo.b_retry_without_resilience4j;

import com.example.resilience4jdemo.exception.IgnoreException;
import com.example.resilience4jdemo.exception.RecordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetryService {

    private static int MAX_ATTEMPS = 3;
    private static int WAIT_DURATION = 1000;

    public String process(String param) throws InterruptedException {
        String result = null;

        int retryCount = 0;

        while (retryCount++ < MAX_ATTEMPS) {
            try {
                result = callAnotherServer(param);
            } catch (RecordException ex) {
                if (retryCount == MAX_ATTEMPS) {
                    return fallback(param, ex);
                }

                Thread.sleep(WAIT_DURATION);
            }
        }

        return result;
    }

    private String fallback(String param, Exception ex) {
        // retry에 전부 실패해야 fallback이 실행
        log.info("fallback! your request is " + param);
        return "Recovered: " + ex.toString();
    }

    private String callAnotherServer(String param) {
        // record exception은 retry된다.
        throw new RecordException("record exception");

        // ignore exception은 retry하지 않고 바로 예외가 클라이언트에게 전달된다.
//            throw new IgnoreException("ignore exception");
    }

};
