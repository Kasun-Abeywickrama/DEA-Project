var counter = 0;

function increment() {
  counter++;
}

function decrement() {
  counter--;
}

function get() {
  return counter;
}

var inc = document.getElementById("increment");
var input = document.getElementById("input");
var dec = document.getElementById("decrement");

inc.addEventListener("click", function() {
  increment();
  input.value = get();
});

dec.addEventListener("click", function() {
  if (input.value > 0) {
    decrement();
  }
  input.value = get();
});