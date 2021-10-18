<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
    <meta charset="UTF-8">
    <title>test page</title>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
    <script src="/assets/js/index.js"></script>
</head>
<body>
    <h1>payment test</h1>
    <button onclick="requestPay()">결제하기</button>

    <script>
        function requestPay(){
            IMP.init('imp33073548'); // 가맹점 식별 코드
            IMP.request_pay({
                pg : 'html5_inicis',                                // PG사 선택 : KG이니시스(웹 표준 결제창)
                pay_method : 'card',                                // 지불 수단 : card
                merchant_uid : 'merchant_' + new Date().getTime(),  // 가맹점에서 구별할 수 있는 고유한 id
                name : '테스트 상품01',                                // 상품명
                amount : 100,                                       // 상품 가격
                buyer_email : 'hrpark@datadesign.engineering',      // 구매자 이메일
                buyer_name : '박혜리',                                // 구매자 이름
                buyer_tel : '010-2222-8947',                        // 구매자 전화번호
                buyer_addr : '서울 구로구 구로동',                       // 구매자 주소
                buyer_postcode : '123-456'                          // 구매자 우편번호
            }, function(rsp) {
                console.log(rsp);
                if ( rsp.success ) {
                    var msg = '결제가 완료되었습니다.';
                    msg += '\n고유ID : ' + rsp.imp_uid;
                    msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                    msg += '\n결제 금액 : ' + rsp.paid_amount;
                    msg += '\n카드 승인번호 : ' + rsp.apply_num;
                } else {
                    var msg = '결제에 실패하였습니다.\n';
                    msg += '에러내용 : ' + rsp.error_msg;
                }
                alert(msg);
            });
        }
    </script>
    
</body>
</html>
