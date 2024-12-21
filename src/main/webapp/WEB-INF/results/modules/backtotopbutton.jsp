<a href="javascript:void(0)" id="backToTop" style="display: none;">
  <div class="backtothetop-button">
    <i class="fa fa-arrow-circle-up" aria-hidden="true"></i>
  </div>
</a>
<script>
  var backToTopButton = document.getElementById("backToTop");

  window.onscroll = function() {
    if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
      backToTopButton.style.display = "flex";
    } else {
      backToTopButton.style.display = "none";
    }
  };

  backToTopButton.addEventListener("click", function() {
    window.scrollTo({
      top: 0,
      behavior: 'smooth'
    });
  });
</script>
