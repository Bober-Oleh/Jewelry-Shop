
document.querySelector('#submit').addEventListener('click', validate);


function validate(e) {

    let first_name = document.querySelector('#first_name').value.trim();
    let last_name = document.querySelector('#last_name').value.trim();
    let email = document.querySelector('#email').value.trim();
    let password = document.querySelector('#password').value.trim();
    let confirm_password = document.querySelector('#confirm_password').value.trim();
    let check = document.querySelector('#check').checked; 
    let filename=document.querySelector('#file').value;
    document.querySelectorAll('.error').forEach(element => element.remove());   
    if(filename!=null){
    let ar=filename.split(".");	
    let extention=ar[ar.length-1].toLowerCase();
    if(extention!=("jpg"||"jpeg"||"png"||"ico")){
    	e.preventDefault();
        document.querySelector('#file')
            .after(
                createNote("Extention may be jpg,png and ico"));
    	
    }
    }
    //if(file)
    //}
    if (first_name.length < 1) {

        e.preventDefault();
        document.querySelector('#first_name')
            .after(
                createNote("This field is required"));
    }
    if (last_name.length < 1) {
        e.preventDefault();
        document.querySelector('#last_name')
            .after(
                createNote("This field is required"));
    }
    if (email.length < 1) {
        e.preventDefault();
        document.querySelector('#email')
            .after(
                createNote("This field is required"))
    } else {
        // let regEx =
		// /^[A-Z0-9][A-Z0-9._%+-]{0,63}@(?:[A-Z0-9-]{1,63}.){1,125}[A-Z]{2,63}$/;
        let regEx = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        let validEmail = regEx.test(email);
        if (!validEmail) {
            e.preventDefault();
            document.querySelector('#email')
                .after(
                    createNote("Enter a valid email")
                );
        }
    }
    if (password.length < 8) {
        e.preventDefault();
        document.querySelector('#password')
            .after(
                createNote("Password must be atleast 8 characterslong")
            );
    }
    if (password != confirm_password) {
        e.preventDefault();
        document.querySelector('#confirm_password')
            .after(createNote("Password must be iquels confirm_password")
            );
    }
    if (!check) {
        e.preventDefault();
        document.querySelector('#check')
            .after(createNote("Check the box")
            );

    }

//    alert("isCorrect="+isCorrect);

      // e.target.submit();


};

function createNote(text) {
    let span = document.createElement('span');
    span.setAttribute('class', 'error');
    span.innerHTML = text;

    return span;
};