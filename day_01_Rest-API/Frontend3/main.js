const getAllUser = (name, password) => {
  fetch(`http://localhost:8080/login?userName=${name}&passWord=${password}`)
  .then(rs => rs.json())
  .then(data => {
    infor.innerHTML = ""
    if(data.userName == undefined){
      const h2 = document.createElement('h2')
      h2.innerText = data.message
      infor.appendChild(h2)
      return
    }
    body.innerHTML = ""
    const username = document.createElement('h3')
    username.innerText = data.userName;
    const email = document.createElement('h3')
    email.innerText = data.email
    const avatar = document.createElement('h3')
    avatar.innerText = data.avatar
    const logout = document.createElement('button')
    logout.innerText = "logout"
    infor.appendChild(username)
    infor.appendChild(email)
    infor.appendChild(avatar)
    infor.appendChild(logout)
    body.appendChild(infor)

    logout.addEventListener('click',function(){
      body.removeChild(infor)
      body.appendChild(login)
    })
  })
  .catch(err => console.log(err))
}

const userName = document.querySelector('.userName')
const passWord = document.querySelector('.passWord')
const submit = document.querySelector('.submit')
const infor = document.querySelector('.infor')
const body =document.querySelector('body')
const login = document.querySelector('.login')

submit.addEventListener('click', function(){
  getAllUser(userName.value, passWord.value)
})