
document.addEventListener("DOMContentLoaded", function () {
	const form = document.querySelector("form");
	if (!form) return;
	
	
	
	form.addEventListener("submit", function(e) {
	        e.preventDefault(); // prevent actual submit
	        //const username = form.querySelector("input[name='username']");
			const nameInput = fdocument.querySelector("input[name='username']");
			const nameError = form.querySelector(".name-error");
	        if (nameInput && nameInput.value.length > 50) {
				nameError.textContent = "Username must be 50 characters or less.";
	            
	            return;
	        }
	        // add other validations here

	        form.submit(); // only if validation passes
	    });
});

function showError(message) {
    const msgBox = document.createElement("div");
    msgBox.textContent = message;
    msgBox.style.color = "red";
    msgBox.style.marginBottom = "10px";
    document.querySelector("form").prepend(msgBox);

    //setTimeout(() => msgBox.remove(), 6000); // remove after 3 sec
}