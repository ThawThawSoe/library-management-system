document.addEventListener("DOMContentLoaded", () => {
  const rowsPerPage = 10;
  let currentPage = 1;

  const table = document.getElementById("userTable");
  const rows = table.getElementsByTagName("tr");
  const totalPages = Math.ceil(rows.length / rowsPerPage);

  function showPage(page) {
    if (page < 1) page = 1;
    if (page > totalPages) page = totalPages;

    for (let i = 0; i < rows.length; i++) {
      rows[i].style.display = "none";
    }

    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    for (let i = start; i < end && i < rows.length; i++) {
      rows[i].style.display = "";
    }

    document.getElementById("prevBtn").disabled = page === 1;
    document.getElementById("nextBtn").disabled = page === totalPages;

    renderPageNumbers();
  }

  function renderPageNumbers() {
    const pageNumbersDiv = document.getElementById("pageNumbers");
    pageNumbersDiv.innerHTML = "";

    for (let i = 1; i <= totalPages; i++) {
      const btn = document.createElement("button");
      btn.textContent = i;
      btn.classList.toggle("active", i === currentPage);
      btn.onclick = function () {
        currentPage = i;
        showPage(currentPage);
      };
      pageNumbersDiv.appendChild(btn);
    }
  }

  window.nextPage = function () {
    if (currentPage < totalPages) {
      currentPage++;
      showPage(currentPage);
    }
  };

  window.prevPage = function () {
    if (currentPage > 1) {
      currentPage--;
      showPage(currentPage);
    }
  };

  const searchBox = document.getElementById("searchBox");
  if (searchBox) {
    searchBox.addEventListener("input", function () {
      const filter = this.value.toLowerCase();
      for (let i = 0; i < rows.length; i++) {
        const cells = rows[i].getElementsByTagName("td");
        const match = Array.from(cells).some(td =>
          td.innerText.toLowerCase().includes(filter)
        );
        rows[i].style.display = match ? "" : "none";
      }
    });
  }

  showPage(currentPage);
});