document.querySelectorAll('.paw-button').forEach(elem => {
    elem.addEventListener('click', e => {
        // document.getElementById("pinkboard").style.display = "block";
        // $("#pinkboard").attr("class","xin_show");
        if(!elem.classList.contains('animation')) {
            elem.classList.add('animation');
            setTimeout(() => {
                elem.classList.remove('animation', 'liked', 'confetti');
                // $("#pinkboard").removeAttr("class");
            }, 600);
        }
        e.preventDefault();
    });
});
// -webkit-animation: fadenum 4s ease;
// -moz-animation: fadenum 4s ease;
// animation:fadenum 4s ease;