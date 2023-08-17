const getCsBmi = (height,weight) => {
  fetch(`http://localhost:8080/bmi?height=${height}&weight=${weight}`)
  .then(rs => rs.json())
  .then(data => {
    if(data.chiSoBmi == undefined){
      h2.innerHTML = data.message
      return
    }
    h2.innerHTML = data.chiSoBmi})
  .catch(err => console.log(err))
}

const heightEl = document.querySelector('.height')
const weightEl = document.querySelector('.weight')
const btnEl = document.querySelector('button')
const h2 = document.querySelector('h2')

btnEl.addEventListener('click',function(){
   let h = heightEl.value
   let w = weightEl.value
  getCsBmi(h,w)
})