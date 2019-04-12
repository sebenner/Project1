function getList(url, display){
	let xmlHttpReq = new XMLHttpRequest()
	xmlHttpReq.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			display(this);
		}
	}
	
	xmlHttpReq.open("GET", url);
	xmlHttpReq.send();
}

getList("/Project1/ViewEmpRequests.do", displayTable);

function displayTable(xmlHttpReq){
	req = JSON.parse(xmlHttpReq.responseText);
	reqTable = document.getElementById("myRequestsTable");
	for (let i in req) {
		tableRow = document.createElement("tr");
		tableRow.innerHTML = `<td>${req[i].submitted}</td>
							<td>${req[i].amount}</td>
							<td>${req[i].type}</td>
							<td>${req[i].status}</td>`;
		reqTable.appendChild(tableRow);
	}
}