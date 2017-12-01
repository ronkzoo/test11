package hello;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * Created by rasset on 2017-12-01.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
public class User implements Serializable {

	private String id;
	private String username;
}
