const getrdColor = (type) => {
  fetch(`http://localhost:8080/random-color?type=${type}`)
  .then(rs => rs.json())
  .then(data => bodyEl.style.backgroundColor = data.nameColor)
  .catch(err => console.log(err))
}

const btnAll = document.querySelectorAll('button')
const bodyEl = document.querySelector('body')

btnAll.forEach(e => {
  e.addEventListener('click', function(){
    getrdColor(e.name)
  })
})
