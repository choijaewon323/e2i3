function complete(){
    window.open("login.html");
}
function signupcheck(){
    let email=document.getElementById("email").value
    let pw=document.getElementById("pw").value
    let name=document.getElementById("name").value
    let univ=document.getElementById("univ").value
    let year=document.getElementById("year").value
    let month=document.getElementById("month").value
    let date=document.getElementById("date").value
    let gender=document.getElementById("gender").value
    let check=true;

  
}

const sign_innForm = document.getElementById('sign_innForm');

sign_innForm.addEventListener('submit', (event) => {
  event.preventDefault(); 

  const formData = new FormData(sign_innForm);
  
  const email = formData.get('email');
  const pw = formData.get('pw');
  const name = formData.get('name');
  const univ = formData.get('univ');
  const year = formData.get('year');
  const month = formData.get('month');
  const date = formData.get('date');
  const gender= formData.get('gender');
  
   
  fetch('/sign_inn', {
    method: 'POST',
    body: JSON.stringify({ email, pw, name, univ,year, month, date, gender}),
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
  .then(response => response.json())
  .then(data => {
    
    if (data.success) {
      
    } else {
      
    }
  })
  .catch(error => {
    
  });
});
