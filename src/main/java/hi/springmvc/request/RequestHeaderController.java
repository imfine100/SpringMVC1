package hi.springmvc.request;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@Slf4j // log 찍을 수 있음
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap, // 헤더 한번에 다 받기
                          @RequestHeader("host") String host, // 헤더 하나만 받기 (예, 필수 헤더 host 하나 받기)
                          // default required true, false 하면 없어도 된다는 뜻
                          @CookieValue(value = "myCookie", required = false) String cookie // value = cookie name
                          ) {
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);
        return "ok";

    }

     /* 참고 : MultiValueMap
        MAP과 유사한 타입으로 하나의 키에 여러가지 값을 받을 수 있음
        HTTP header, HTTP 쿼리 파라미터와 같이 하나의 키에 여러가지 값을 받을 때 사용함
        KeyA=value1&KeyA=value2
        *
        * */

    public static void main(String[] args) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("keyA", "value1");
        map.add("keyA", "value2");

        List<String> values = map.get("KeyA"); //[value1, value2]
    }

}
