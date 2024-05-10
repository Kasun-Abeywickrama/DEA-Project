<%-- 
    Document   : about_us
    Created on : Apr 30, 2024, 10:26:35 PM
    Author     : dewmi
--%>


<%@ include file="header_part_01.jsp"%>
<title>About us</title>
<%@ include file="header_part_02.jsp"%>

<style>
    .wrapper {
        padding-top: 20px;
        /*background-color: red;*/
    }
    @media screen and (max-width : 992px) {
        .wrapper {
            padding-top: 50px;
        }

    }

    .container-fluid .container .jumbotron {
        padding: 20px;
    }
    .row .col-lg-6 h1 {
        font-weight: 400;
        text-align: center;
        color: #000000;
    }
    .row .col-lg-6 p {
        font-size: 15px;
        text-align: justify;
    }
    .row .col-lg-12 .display-4 {
        font-weight: 400;
        text-align: center;
    }
    .row .col-lg-12 p {
        font-size: 15px;
        text-align: justify;
    }

    .img-fluid1{
        width: 400px;
        height: 250px;
    }

    .img-fluid2{
        width: 700px;
        height: 450px;
    }





</style>

<div class="container-fluid wrapper">

    <div class="container wrapper">
        <div class="jumbotron mb-5">
            <div class="row">
                <div class="col-lg-6">
                    <img src="images/about_us_images/aboutimg.jpeg" class="img-fluid1" alt="Placeholder image">
                </div>
                <div class="col-lg-6">
                    <h1 class="display-4">WHO WE ARE?</h1>
                    <p>This is a furniture shop that is recognized worldwide. We are trusted by all of our customers and suppliers all around the world. This website gives the ability for our customers to engage with us online to buy furniture without going to the shop physically. This web site is created by a group of encouraged students of the NSBM Green University. Scroll down below to see information about the NSBM Green University.</p>
                    
                    <br><br><br><br><br>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="display-4">
                        <div>NSBM GREEN UNIVERSITY</div>
                    </h1>
                    <p>NSBM Green University, the nation?s premier degree-awarding institute, is the first of 
                        its kind in South Asia. It is a government-owned self-financed institute that operates 
                        under the purview of the Ministry of Education. As a leading educational center in the 
                        country, NSBM has evolved into becoming a highly responsible higher education 
                        institute that offers unique opportunities and holistic education on par with international 
                        standards while promoting sustainable living.
                        NSBM offers a plethora of undergraduate and postgraduate degree programs under 
                        five faculties: Business, Computing, Engineering, Science and Postgraduate and 
                        Professional Advancement. These study programs at NSBM are either its own 
                        programs recognied by the University Grants Commission and the Ministry of 
                        Higher Education or world-class international programs conducted in affiliation with 
                        top-ranked foreign universities such as University of Plymouth, UK, and Victoria 
                        University, Australia. 
                        Focused on producing competent professionals and innovative entrepreneurs for the 
                        increasingly globalizing world, NSBM nurtures its graduates to become productive 
                        citizens of society with their specialization ranging in study fields such as Business, 
                        Management, Computing, IT, Engineering, Science, Psychology, Nursing, Interior 
                        design, Quantity Surveying, Law and Multimedia.
                        Inspired by the vision of being Sri Lanka?s best-performing graduate school and being 
                        recognized internationally, NSBM currently achieves approximately 3000 new 
                        enrollments per year and houses above 11,000 students reading over 50 degree 
                        programs under 4 faculties. Moreover, over the years, NSBM Green University has 
                        gifted the nation with 14,000+ graduates and has proved its global presence with an 
                        alumni network spread all across the world.
                        Nestling on a 40-acre land amidst the greenery and serenity in Pitipana, Homagama, 
                        NSBM Green University, is an ultra-modern university complex constructed with state of-the-art facilities and amenities that provides the perfect setting for high-quality</p>
                    <p>Web Link: <a href="https://www.nsbm.ac.lk/"> Story of NSBM | NSBM </a></p>
                    <center><a href="https://www.nsbm.ac.lk/"><img src="images/about_us_images/4-NSBM.jpg" class="img-fluid2"></a></center></div>
            </div>
        </div>
    </div>

</div>
<script>
    var tabs = document.querySelectorAll('.nav-link');
    tabs.forEach(function (tab) {
        tab.classList.remove('active');
    });
    document.getElementById('nav-link-about-us').classList.add('active');
</script>

<%@ include file="footer.jsp"%>
