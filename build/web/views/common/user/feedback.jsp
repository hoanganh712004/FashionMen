<%-- 
    Document   : feedback
    Created on : Feb 26, 2024, 6:39:26 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="testimonial-section">
    <div class="container">
        <div class="ev-top-content">
            <span>FeedBack</span>
            <h2>What People Say About us</h2>
            <p>Elevate your style effortlessly. Discover curated men's fashion at its finest – simplicity meets sophistication.</p>
        </div>
        
        <div class="swiper testimonial-slider testimonial-items">
            <div class="swiper-wrapper">

                <c:forEach items="${feedBackAll}" var="feedback">
                    <div class="swiper-slide single-testimonial">
                        <div class="testimonial-author d-flex align-items-center justify-content-between">
                            <div class="author-bio d-flex align-items-center">
                                <div class="author-img">
                                    <img class="rounded-circle" max-width="100%" height="80px" src="${pageContext.request.contextPath}/${feedback.userImage}" alt="testimonial">
                                </div>
                                <div class="author-text">
                                    <h6>${feedback.username}</h6>
                                    <span>Subject: ${feedback.subjectName}</span>
                                    <span>Date: ${feedback.feedbackDate}</span>
                                </div>
                            </div>
                            <div class="quote-icon">
                                <img src="${pageContext.request.contextPath}/images/index-1/testimonial/quot/shape-1.png" alt="testimonial">
                            </div>
                        </div>
                        <div class="testimonial-text">
                            <p>${feedback.note}</p>
                        </div>
                    </div>
                </c:forEach>



<!--            <div class="swiper-button-next hero-slide-btn testimonial-slide-btn">
                <i class="fa-solid fa-arrow-right"></i>
            </div>
            <div class="swiper-button-prev hero-slide-btn testimonial-slide-btn">
                <i class="fa-solid fa-arrow-left"></i>
            </div>-->
        </div>
    </div>
</section>