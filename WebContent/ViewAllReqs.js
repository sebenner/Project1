function getList(url, display){
	let xmlHttpReq = new XMLHttpRequest()
	xmlHttpReq.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			display(this);
		}
	}
	
	xmlHttpReq.open("GET", url, true);
	xmlHttpReq.send();
}

function postList(url, display){
	let xmlHttpReq = new XMLHttpRequest()
	xmlHttpReq.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			display(this);
		}
	}
	
	xmlHttpReq.open("POST", url, true);
	xmlHttpReq.send();
}

getList("/Project1/ViewAllRequests.do?status=All", displayTable);



let dropStatus = document.getElementById("newStatus");
dropStatus.onchange = function() {
	clearList();
	reqTable = document.getElementById("requestsTable");
	tableRow = document.createElement("tr");
	tableRow.innerHTML ="<tr><th>Employee Id</th><th>Username</th><th>Amount</th><th>Submission Date</th><th>Type</th><th>Status</th></tr>";
	reqTable.appendChild(tableRow);
	getList("/Project1/ViewAllRequests.do?status=" + dropStatus.value, displayTable);
}

function clearList() {
	let table = document.getElementById("requestsTable")
	while(table.firstChild) {
		table.removeChild(table.firstChild);
	}
}

function displayTable(xmlHttpReq){
	req = JSON.parse(xmlHttpReq.responseText);
	reqTable = document.getElementById("requestsTable");
	for (let i in req) {
		tableRow = document.createElement("tr");
		tableRow.innerHTML = `<td>${req[i].id}</td>
							<td>${req[i].username}</td>
							<td>${req[i].amount}</td>
							<td>${req[i].submitted}</td>
							<td>${req[i].type}</td>
							<td>${req[i].status}</td>`;
		if (req[i].status=='Pending') {
			tableRow.innerHTML += `<td><form action="Approve.do" method="POST"> <button value=${req[i].id} type='submit' class='btn btn-primary btn-sm' name="Approve">Approve</button>` + 
			`<button value=${req[i].id} type='submit' class='btn btn-secondary btn-sm' name="Deny">Deny</button></form></td>`;
		}
		reqTable.appendChild(tableRow);
	}
}