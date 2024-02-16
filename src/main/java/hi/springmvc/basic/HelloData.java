package hi.springmvc.basic;

import lombok.Data;

/**
 * @Data 역할
 * @Getter , @Setter , @ToString ,
 * @EqualsAndHashCode , @RequiredArgsConstructor 를
 * 자동으로 적용해줌
 */
@Data //
public class HelloData {
    private String username;
    private int age;
}
