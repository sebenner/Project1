package repository;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Request {
	private int id;
	private String username;
	private float amount;
	//private Date submitted;
	private String submitted;
	private String description;
	//private int resolver;
	private String type;
	private String status;
}

/*
select r.reimbid, u.usrUsername, r.reimbamount, r.reimbsubmitted, r.reimbdescription, r.reimbtype, r.reimbstatus
from ers_users u JOIN ers_reimbursement r ON u.usrId = r.reimbauthor
where status = ?;
*/