package hello;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Greeting {

    private String content;
    private User  user;

}
