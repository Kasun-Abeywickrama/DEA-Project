function setMainCategoriesToHeader(mainCategories) {
    var dropdownMenu = document.querySelector(".dropdown-menu");

    dropdownMenu.innerHTML = "";

    mainCategories.forEach(function (mainCategory) {
        var menuItem = document.createElement("li");

        var menuLink = document.createElement("a");
        menuLink.classList.add("dropdown-item");
        menuLink.href = "ReadProductsByCategoriesServlet?main_category_id=" + mainCategory.mainCategoryId + "&main_category_name=" + mainCategory.mainCategoryName;
        menuLink.textContent = mainCategory.mainCategoryName;

        menuItem.appendChild(menuLink);
        dropdownMenu.appendChild(menuItem);
    });

}

function getMainCategoriesToHeader() {
//                    setActiveTab('pending');
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "ReadCategoryServlet?type=allMainCategories", true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var responseData = JSON.parse(xhr.responseText);
            setMainCategoriesToHeader(responseData);
        }
    };
    xhr.send();
}

getMainCategoriesToHeader();