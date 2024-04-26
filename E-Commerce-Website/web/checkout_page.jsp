<%-- 
    Document   : checkout_page
    Created on : Apr 25, 2024, 11:56:24 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   
    <link href="https://cdn.jsdelivr.net/npm/remixicon@4.2.0/fonts/remixicon.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/checkout_page.css">
    <link href="css/bootstrap-4.4.1.css" rel="stylesheet">

</head>

<body>
    <section>
        <div class="box">
            <div class="d-p">
                <button><i class="ri-add-circle-line"></i>&nbsp;Add New Delivery Address</button>
                <button><i class="ri-add-circle-line"></i>&nbsp;Select Payment Method</button>
            </div>
            <div class="content">
              <div class="row">
                <!--Item with image-->
                <div class="col-lg-7">
                <div class="con-de">
                    <table  border="0" cellspacing="20" cellpadding="0">
                        <tbody>
                          <tr>
                              <td width="212" height="153"><img src="images/product_images/Rectangle 20.png" width="193" height="131" alt=""/></td>
                            <td width="396"><h3>William Standard Dinning Table with Chairs</h3>
                            <p>Teak : 36'W x 72'L	  </p><br>
                            <table width="393" border="0" cellspacing="0" cellpadding="0">
                              <tbody>
                                <tr>
                                  <td><strong>Rs.100,000.00</strong></td>
                                  <td>Quantity :1</td>
                                  </tr>
                                </tbody>
                              </table>
                            <p>&nbsp;</p></td>
                          </tr>
                          <!--From here -->
                          <tr>
                            <td height="149"><img src="images/product_images/Rectangle 20.png" width="193" height="131" alt=""/></td>
                            <td><h3>William Standard Dinning Table with Chairs</h3>
                              <p>Teak : 36'W x 72'L        </p><br>
                              <table  border="0" cellspacing="0" cellpadding="0">
                                <tbody>
                          <tr>
                            <td><strong>Rs.100,000.00</strong></td>
                            <td>Quantity : 1</td>
                          </tr>
                        </tbody>
                      </table>
                              <p>&nbsp;</p>
                      
                            </td>
                          </tr> 
                        <!--To here -->
                        </tbody>
                      </table>
                </div>
                </div>
                <!--Order Summary-->
                <div class="col-lg-5">
                <div class="c-s">
                    <div class="c-s-t">
                    <table  border="0" cellspacing="0" cellpadding="0">
                        <tbody>
                          <tr>
                            <td width="219" height="87"><h3 style="font-size: 24px">Order Summary</h3></td>
                            <td width="199">&nbsp;</td>
                          </tr>
                          <tr>
                            <td>Sub Total</td>
                            <td align="right"><blockquote>
                              <p>Rs.200,000.00</p>
                            </blockquote></td>
                          </tr>
                          <tr>
                            <td height="50">Delivery Fee</td>
                            <td align="right"><blockquote>
                              <p>Rs.2,000.00</p>
                            </blockquote></td>
                          </tr>
                          <tr>
                            <td>Discount</td>
                            <td align="right"><blockquote>
                              <p>0</p>
                            </blockquote></td>
                          </tr>
                          <tr>
                            <td height="84"><strong>Total</strong></td>
                            <td align="right"><blockquote>
                              <p><strong>Rs.202,000.00</strong></p>
                            </blockquote></td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                      <center><button>Place Order</button></center>
                </div>
            </div>
            </div>
        </div> 
        </div>   
    </section>

</body>

</html>




