console.log("'contract-script.js from contractb loaded")

function toggle() {
	p = document.getElementById("details");
	if (p.getAttribute("hidden") === null) {
		p.setAttribute("hidden", "");
	} else {
		p.removeAttribute("hidden");	
	}
}