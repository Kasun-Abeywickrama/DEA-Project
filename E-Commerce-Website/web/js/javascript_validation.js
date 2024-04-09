/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//stock_edit_page form validation
function add_stock_validate(){
    
    if(isNaN(document.add_stock_form.add_stock.value)){
        alert("Please enter a valid number");
        return false;
    }
    
    if(document.add_stock_form.add_stock.value <= 0){
        alert("Please enter a valid number");
        return false;
    }
    
    return true;
}

//stock_edit_page form validation
function remove_stock_validate(){
    
    if(isNaN(document.remove_stock_form.remove_stock.value)){
        alert("Please enter a valid number");
        return false;
    }
    
    if(document.remove_stock_form.remove_stock.value <= 0){
        alert("Please enter a valid number");
        return false;
    }
    
    return true;
}

