<%-- 
    Document   : admin_dashboard_sidebar
    Created on : Apr 16, 2024, 8:41:13 PM
    Author     : Sithuruwan
--%>

<script>
    //When the button name is inserted to this function, the related li tag will displayed in black
    function default_active_button(btn_name){
        if(btn_name === "dashboard"){
            dashboardButton = document.querySelector(".sidebar ul li:nth-child(1) a");
            dashboardButton.classList.add("active_li");
        }
        
        if(btn_name === "orders"){
            ordersButton = document.querySelector(".sidebar ul li:nth-child(2) a");
            ordersButton.classList.add("active_li");
        }
        
        if(btn_name === "inventory"){
            inventoryButton = document.querySelector(".sidebar ul li:nth-child(3) a");
            inventoryButton.classList.add("active_li");
        }
        
        if(btn_name === "categories"){
            categoriesButton = document.querySelector(".sidebar ul li:nth-child(4) a");
            categoriesButton.classList.add("active_li");
        }
    };
</script>

<div class="sidebar">
    
    <div class="top">
        <div class="logo">
            <a href="#">
                <i class="bx bxs-grid-alt"></i>
                <div>Elegant Furniture</div>
            </a>
        </div>
    </div>
    
    <ul>
        <li>
            <a href="#">
                <i class="bx bxs-grid-alt"></i>
                <div class="nav-item">Dashboard</div>
            </a>
        </li>
        <li>
            <a href="#">
                <i class="bx bxs-purchase-tag-alt"></i>
                <div class="nav-item">Orders</div>
            </a>
        </li>
        <li>
            <a href="#">
                <i class="bx bx-package"></i>
                <div class="nav-item">Inventory</div>
            </a>
        </li>
        <li>
            <a href="#">
                <i class="bx bx-category"></i>
                <div class="nav-item">Categories</div>
            </a>
        </li>
    </ul>
</div>


