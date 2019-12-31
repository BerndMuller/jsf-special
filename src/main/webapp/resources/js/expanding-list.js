class ExpandingList extends HTMLUListElement {
	
  constructor() {
    super();
    window.onload = function() {
      var tree = document.querySelector("#tree");
      const uls = Array.from(tree.querySelectorAll('ul'));
      const lis = Array.from(tree.querySelectorAll('li'));

      uls.slice(1).forEach(ul => {
        ul.style.display = 'none';
      });

      lis.forEach(li => {
        const childText = li.childNodes[0];
        const newSpan = document.createElement('span');
        newSpan.textContent = childText.textContent;
        childText.parentNode.insertBefore(newSpan, childText);
        childText.parentNode.removeChild(childText);
      });

      const spans = Array.from(tree.querySelectorAll('span'));

      spans.forEach(span => {
        if (span.nextElementSibling) {
          span.style.cursor = 'pointer';
          span.parentNode.setAttribute('class', 'closed');
          span.onclick = showul;
        }
      });

      function showul(e) {
        const nextul = e.target.nextElementSibling;

        if (nextul.style.display == 'block') {
          nextul.style.display = 'none';
          nextul.parentNode.setAttribute('class', 'closed');
        } else {
          nextul.style.display = 'block';
          nextul.parentNode.setAttribute('class', 'open');
        }
      }
    };
  }
}

customElements.define('expanding-list', ExpandingList, { extends: 'ul' });
