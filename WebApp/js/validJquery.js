$(document)
		.ready(
				function() {
					$('#form')
							.submit(
									function(e) {
										e.preventDefault();
										let first_name = $('#first_name').val().trim();
										let last_name = $('#last_name').val().trim();
										let email = $('#email').val().trim();
										let password = $('#password').val().trim();
										
										let confirm_password = $(
												'#confirm_password').val().trim();
										let check=$('#check').checked;
										$(".error").remove();

										if (first_name.length < 1) {
											$('#first_name')
													.after(
															'<span class="error">This field is required</span>');
										}
										if (last_name.length < 1) {
											$('#last_name')
													.after(
															'<span class="error">This field is required</span>');
										}
										if (email.length < 1) {
											$('#email')
													.after(
															'<span class="error">This field is required</span>');
										} else {
											let regEx = /^[A-Z0-9][A-Z0-9._%+-]{0,63}@(?:[A-Z0-9-]{1,63}.){1,125}[A-Z]{2,63}$/;
											//let regEx = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
											let validEmail = regEx.test(email);
											if (!validEmail) {
												$('#email')
														.after(
																'<span class="error">Enter a valid email</span>');
											}
										}
										if (password.length < 8) {
											$('#password')
													.after(
															'<span class="error">Password must be atleast 8 characterslong</span>');
										}
										if (password != confirm_password) {
											$('#confirm_password')
											.after(
													'<span class="error">Password must be iquels confirm_password</span>');
										}
										if(!check){
											$('#check')
											.after(
													'<span class="error">check the box</span>');
											
										}
										

									});
				});