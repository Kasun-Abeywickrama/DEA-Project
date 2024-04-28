<%-- 
    Document   : Delivery_details
    Created on : Apr 26, 2024, 12:43:44 PM
    Author     : Geshan Sampath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Address</title>
        <script src="https://cdn.tailwindcss.com?plugins=forms,typography,aspect-ratio,line-clamp"></script>

    <script>
        
        tailwind.config = {
          theme: {
            extend: {
              colors: {
                clifford: '#da373d',
              }
            }
          }
        }
      </script>
    </head>
    <body class="bg-[#FAF9F6]">
        
        <%@ include file="header.jsp"%> 
        
        
    <div class="form bg-white p-4 m-4 w-1/2 mx-auto border rounded-xl" id="2nd_form">
    <form action="deliverydetails" method="POST" >
        <ul class="p-4">
            <li><h5>Full Name *</h5></li>
            <li class="grid "><input type="text" name="fname"  placeholder="Input full name" class="border-none"></li>
        </ul>
        <ul class="p-4">
            <li><h5>Province *</h5></li>
            <li class="grid "> <select name="province" id="province" class="border-none">
                <option value="" disabled selected >Please choose your province</option>
                <option value="Western">Western</option>
                <option value="NorthWestern">North Western</option>
                <option value="Central">Central</option>
                <option value="NorthCentral">North Central</option>
                <option value="Southern">Southern</option>
                <option value="Eastern">Eastern</option>
                <option value="Northern">Northern</option>
                <option value="Uva">Uva</option>
                <option value="Sabaragamuwa">Sabaragamuwa</option>
                
               
            </select></li>
        </ul>

        <ul class="p-4">
            <li><h5>City *</h5></li>
            <li class="grid py-2"><select name="city" id="city" class="border-none">
                <option value="" disabled selected>Please choose your city</option>
                </select>
            </li>
       </ul>
        
        

        <ul class="p-4">
            <li><h5>Area *</h5></li>
            <li class="grid py-2"><input type="text" name="area"  placeholder="Input your area" class="border-none"></li>
            </ul>


        <ul class="px-4">
            <li><h5>Address *</h5></li>
            <li class="grid py-2"><input type="text" name="address"  placeholder="Input full Address" class="border-none"></li>
        </ul>

        <ul class="p-4">
            <li><h5>Mobile Number *</h5></li>
            <li class="grid py-2"><input type="text" name="tel"  placeholder="Input mobile number" class="border-none"></li>
        </ul>
        <ul class="p-4">
            <li class="grid grid-col-3"><input type="submit" value="Save" class="col-3 justify-self-end bg-[#FC6B03] px-4 py-2 rounded-md text-white font-semibold"></li>
        </ul>

        
    </form>

  </div>

  <script>
    const citiesByProvince = {
       Western: ["Colombo", "Kaluthara", "Gampaha"],
        NorthWestern: ["Puttalam", "Kurunagala"],
        Central: ["Matale", "Kandy", "Nuwara Eliya"],
        NorthCentral: ["Anuradhapura", "Polonnaruwa"],
        Southern: ["Hambanthota", "Galle", "Matara"],
        Eastern: ["Trincomale", "Batticaloa", "Ampara"],
        Northern: ["Jaffna", "Killinochchi", "Mannar","Mulativu","Vavuniya"],
        Uva: ["Badulla", "Monaragala"],
        Sabaragamuwa: ["Kegalla", "Ratnapura"]
    };

    const provinceSelect = document.getElementById('province');
    const citySelect = document.getElementById('city');

    provinceSelect.addEventListener('change', function() {
        const selectedProvince = this.value;
        citySelect.innerHTML = ''; 

        if (selectedProvince === '') {
            citySelect.disabled = true;
            citySelect.innerHTML = '<option value="" disabled selected>Please choose your city</option>';
            return;
        }

        const cities = citiesByProvince[selectedProvince];
        citySelect.disabled = false;

        cities.forEach(city => {
            const option = document.createElement('option');
            option.value = city;
            option.textContent = city;
            citySelect.appendChild(option);
        });
    });
</script>
 <%@ include file="footer.jsp"%>
    </body>
</html>
