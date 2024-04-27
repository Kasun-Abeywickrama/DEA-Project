/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//sign up page validation
function sign_up_validate(){
    
    if(document.sign_up_form.username.value === "" || document.sign_up_form.password.value === "" || document.sign_up_form.re_password.value === ""){
        alert("Please enter all the relevant details");
        return false;
    }
    
    if(document.sign_up_form.password.value.length < 8){
        alert("The Password must have at least 8 characters");
        return false;
    }
    
    if(document.sign_up_form.password.value !== document.sign_up_form.re_password.value){
        alert("The provided passwords does not match");
        return false;
    }
    
    return true;
}


//stock_update_page form validation
function stock_update_add_quantity_validate(){
    
    if(isNaN(document.add_quantity_form.add_quantity.value)){
        alert("Please enter a valid quantity");
        return false;
    }
    
    if(document.add_quantity_form.add_quantity.value <= 0){
        alert("Please enter a valid quantity");
        return false;
    }
    
    if((document.add_quantity_form.add_quantity.value).includes(".")){
        alert("Please enter a valid quantity");
        return false;
    }
    
    return true;
}



//stock_update_page form validation
function stock_update_remove_quantity_validate(){
    
    if(isNaN(document.remove_quantity_form.remove_quantity.value)){
        alert("Please enter a valid quantity");
        return false;
    }
    
    if(document.remove_quantity_form.remove_quantity.value <= 0){
        alert("Please enter a valid quantity");
        return false;
    }
    
    if((document.remove_quantity_form.remove_quantity.value).includes(".")){
        alert("Please enter a valid quantity");
        return false;
    }
    
    return true;
}



//stock_update_page form validation
function stock_update_details_validate(){
    
    if(document.stock_details_update_form.supplier_name.value === ""){
        alert("Please enter the supplier name");
        return false;
    }
    
    if(document.stock_details_update_form.buying_price.value === ""){
        alert("Please enter a valid buying price");
        return false;
    }
    
    if(isNaN(document.stock_details_update_form.buying_price.value)){
        alert("Please enter a valid buying price");
        return false;
    }
    
    return true;
}




//stock_add_page form validation
function stock_add_validate(){
    
    if(document.stock_add_form.supplier_name.value === ""){
        alert("Please enter the supplier name");
        return false;
    }
    
    if(document.stock_add_form.buying_price.value === ""){
        alert("Please enter the buying price");
        return false;
    }
    
    if(isNaN(document.stock_add_form.buying_price.value)){
        alert("Please enter a valid buying price");
        return false;
    }
    
    if(isNaN(document.stock_add_form.quantity.value)){
        alert("Please enter a valid quantity");
        return false;
    }
    
    if(document.stock_add_form.quantity.value <= 0){
        alert("Please enter a valid quantity");
        return false;
    }
    
    if((document.stock_add_form.quantity.value).includes(".")){
        alert("Please enter a valid quantity");
        return false;
    }
    
    return true;
}

