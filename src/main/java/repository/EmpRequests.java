package repository;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmpRequests {
	//private Date submitted;
	private String submitted;
	private float amount;
	private String type;
	private String status;
}
