package hi.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 기본 요청
     * 둘 다 허용 /hello-basic, /hello-basic/
     * HTTP 메서드 모두 허용, GET, HEAD, POST, PUT PATCH, DELETE
     * ({"/hello-basic", "/hello-go"}) 배열 형태로 여러가지 url 설정 가능
     * */

    // GET 이외의 요청할 경우, HTTP 405(Method Not Allowed) 반환
    @RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
    public String helloBasic(){
        log.info("helloBasic");
        return "OK";
    }

    /**
     * 편리한 축약 애노테이션 (코드보기)
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }


    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable String userId
     *  /mapping/userA 형태로 경로가 들어옴
     *  경로에 매핑되는 값을 가져올 수 있음
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    /**
     * PathVariable 사용 다중
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long
            orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * HTTP 요청의 Content-Type 헤더를 기반으로 미디어 타입으로 매핑한다, consume (=소비하는 입장에서 생각)
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */

    // 요청이 맞지 않으면 HTTP 415 상태코드(Unsupported Media Type)을 반환
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)  //applcation/json만 받아들일 수 있어
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * HTTP 요청의 Accept 헤더를 기반으로 미디어 타입으로 매핑, produces(=생산하는 입장으로 생각)
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */

    // 요청이 맞지 않으면  HTTP 406 상태코드(Not Acceptable)을 반환한다.
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE) //text/html만 생산함
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }


}
