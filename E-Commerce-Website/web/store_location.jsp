<%-- 
    Document   : store_location
    Created on : Apr 30, 2024, 6:52:05 PM
    Author     : dewmi
--%>

<%@ include file="header_part_01.jsp"%>
<%@ include file="header_part_02.jsp"%>

<div class="container-fluid map-wrapper">
            <div class="container" >
                <h2 class="text-center my-5">Our location</h2>
                <iframe class="map-loct mb-5"
                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3961.582438458324!2d80.03778166477238!3d6.820531295070536!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3ae2523b05555555%3A0x546c34cd99f6f488!2sNSBM%20Green%20University!5e0!3m2!1sen!2slk!4v1690898785769!5m2!1sen!2slk"
                    width="100%" height="400px" style="border:0;" allowfullscreen="" loading="lazy"
                    referrerpolicy="no-referrer-when-downgrade" ></iframe>
            </div>
        </div>

<script>
    var tabs = document.querySelectorAll('.nav-link');
    tabs.forEach(function (tab) {
        tab.classList.remove('active');
    });
    document.getElementById('nav-link-store-location').classList.add('active');
</script>
<%@ include file="footer.jsp"%>

