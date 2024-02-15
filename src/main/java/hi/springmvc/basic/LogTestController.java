package hi.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //lombok이 제공하는 어노테이션, Logger 역할을 함
@RestController
public class LogTestController {
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        //단순한 콘솔 출력만으로는 정보를 확인할 수 없음!!
        //System.out.println("name = " + name);



        // +를 쓰는 경우 문자열 연산이 일어남(trace mylog=Spring)
        //  의미 없는 연산에 의한 메모리 손실도 발생함! 주의할것.
        // log.trace("trace mylog=" + name);


        log.trace("trace log={}", name);
        log.debug("debug log={}", name); // 주로 개발 서버에서 많이 씀
        log.info(" info log={}", name); // 정보, 운영서버에서는 info 레벨로 설정해놓음
        log.warn(" warn info={}", name); // 위험
        log.error("error info={}", name); // 에러, 제일 등급 높음

        return "ok";
    }
}
