$(document).ready(function () {

    let estimateNum; //견적번호
    let volume = [];
    let pageIndex = 0;
    let page_01 = false;
    let page_02_01 = false;
    let page_02_01_01 = false;
    let page_02_02 = false;
    // let page_02_03 = false;
    let page_02_03_01 = false;
    let page_02_04 = false;
    let page_02_04_01 = false;
    let page_02_06 = false;
    let page_02_06_01 = false;
    let page_02_07 = false;
    let page_03 = false;
    let page_03_01 = false;//상담시간 선택
    //총 13page
    let theCalendar = new wijmo.input.Calendar('#theCalendar');  //위즈모달력

    var today = _getFormatDate(new Date());
    $('#sample_date').attr('min',today);
    $('#est_sample_date').attr('min',today);


    // header
    $('.header .gnb li:first-child').addClass('current');
    let liFirWidth = $('.header .gnb li:first-child').width();
    $('.header .gnb .gnb_bar').css('width', liFirWidth);
    // $('.header .gnb li').click(function () {
    //     $('.header .gnb li').removeClass('current');
    //     $(this).addClass('current');
    //     let gnbBarWidth = $(this).width();
    //     let gnbLiLeft = $(this).position().left;
    //     $('.header .gnb .gnb_bar').css({
    //         'width': gnbBarWidth,
    //         'left': gnbLiLeft,
    //     });
    // });

    //header 화면 전환
    $(".flex_wrap > li:nth-of-type(1)").click(function () {
        $(".page-switch").removeClass("on");
        $(".page-switch.page01").addClass("on");
        pageIndex = 0;
        checkPageIndex(pageIndex);
    });
    $(".flex_wrap > li:nth-of-type(2)").click(function () {
        if (!page_01) {
            alert("기본정보를 입력해주시기 바랍니다.");
            return false;
        } else {
            $(".page-switch").removeClass("on");
            $(".page-switch.page02_01").addClass("on");
            pageIndex = 1;
            $('.tit').text("용도");
            checkPageIndex(pageIndex);
        }
    });
    $(".flex_wrap > li:nth-of-type(3)").click(function () {
        if (!page_02_07) {
            alert("배터리요구사항 작성을 완료하지 않았습니다.");
            return false;
        } else {
            $(".page-switch").removeClass("on");
            $(".page-switch.page03").addClass("on");
            pageIndex = 10;
            $('.tit').text("견적서확인");
            checkPageIndex(pageIndex);
        }
    });
    $(".flex_wrap > li:nth-of-type(4)").click(function () {
        if (!page_02_07) {
            alert("배터리요구사항 작성을 완료하지 않았습니다.");
            return false;
        } else {
            $(".page-switch").removeClass("on");
            $(".page-switch.page04").addClass("on");
            $('.tit').text("기술상담 예약");
            pageIndex = 11;
            checkPageIndex(pageIndex);
        }
    });


    //next버튼 클릭
    $(".arrow_btn.next").click(function (e) {
        // e.preventDefault();

        validationCheck();

    });

    //prev 버튼 클릭
    $(".arrow_btn.prev").click(function () {
        prevValidationCheck();
    });


    if ($(window).width() <= 1200) {
        $('.require_triger').click(function () {
            $('.require_wrap').toggleClass('open');
            $('.content').toggleClass('before');
        });
        $(document).mouseup(function (e) {
            if ($('.require_wrap').has(e.target).length === 0) {
                $('.require_wrap').removeClass('open');
            }
        });
    }

    // 참조
    $('.page02_btn').click(function () {
        $('.content').attr('class', 'content');
        $('.content').toggleClass('pg_layout02');
    });

    $('.page03_btn').click(function () {
        $('.content').attr('class', 'content');
        $('.content').toggleClass('pg_layout03');
    });


    // $(".page02_step > li").click(function () {

    //     console.log("현재페이지" + pageIndex);

    //     if(pageIndex < $(this).index()+1){
    //         let chkError = validationCheck();

    //         console.log(chkError);

    //         if(typeof chkError == undefined){
    //             if(pageIndex == 3){
    //                 console.log("c");
    //                 $('#battery').text() == '리튬인산철' ? pageIndex = $(this).index()+2 : pageIndex = $(this).index()+1;
    //             }

    //             pageChange();
    //         }
    //     }

    //     if(pageIndex > $(this).index()+1){
    //         pageIndex = $(this).index()+2;
    //         prevPageChange();
    //     }

    //     console.log("이동후 페이지" + pageIndex);


    //     // pageIndex = $(this).index()+1;
    //     //
    //     // checkPageIndex(pageIndex);

    //     // let chkError = validationCheck();
    //     //
    //     // if(chkError){
    //     //
    //     // }

    // });

    function prevPageChange() {
        pageIndex--;

        $(".arrow_btn.next").addClass("on");
        checkPageIndex(pageIndex);
    }

    //다음페이지 이동
    function pageChange(pageError) {
        if (pageError == true) {
            pageIndex++;

            if (pageIndex > 12) {
                pageIndex = 0;
            }

            checkPageIndex(pageIndex);

            $(".arrow_btn.prev").addClass("on");
        }
    }

    function checkPageIndex(pageIndex) {
        let gnbBarWidth;
        let gnbLiLeft;
        console.log(pageIndex);
        if (pageIndex == 0) {
            $('.tit').text("상담을 위한 기본정보를 입력해주세요.");
            $('.require_wrap').removeClass('on');
            $('.content_tit_wrap').removeClass('on');
            $('.content_tit_wrap .page02_step').removeClass('on');
            $('.content_tit_wrap').css('width', $('.page-switch.page01').width());
            $('.header .gnb li').removeClass('current');
            $('.header .gnb li').eq(0).addClass('current');
            $('.content.pg_layout01 .arrow_btn.prev').removeClass('on');
            $('.content.pg_layout01 .arrow_btn.next').addClass('on');
            gnbBarWidth = $('.header .gnb li').eq(0).width();
            gnbLiLeft = $('.header .gnb li').eq(0).position().left;
            $('.header .gnb .gnb_bar').css({
                'width': gnbBarWidth,
                'left': gnbLiLeft,
            });
        }
        if (pageIndex > 0 && pageIndex < 10) {
            $('.require_wrap').addClass('on');
            $('.content_tit_wrap').addClass('on');
            $('.content_tit_wrap .page02_step').addClass('on');
            $('.content_tit_wrap').css('width', '100%');
            $('.header .gnb li').removeClass('current');
            $('.header .gnb li').eq(1).addClass('current');
            $('.arrow_btn').addClass('on');
            gnbBarWidth = $('.header .gnb li').eq(1).width();
            gnbLiLeft = $('.header .gnb li').eq(1).position().left;
            $('.header .gnb .gnb_bar').css({
                'width': gnbBarWidth,
                'left': gnbLiLeft,
            });
        }
        if (pageIndex == 10) {
            $('.content_tit_wrap').removeClass('on');
            $(".require_wrap").removeClass("on");
            $(".content_tit_wrap .page02_step").removeClass("on");
            $('.content_tit_wrap').css('width', '100%');
            $('.header .gnb li').removeClass('current');
            $('.header .gnb li').eq(2).addClass("current");
            $('.arrow_btn').addClass("on");
            gnbBarWidth = $('.header .gnb li').eq(2).width();
            gnbLiLeft = $('.header .gnb li').eq(2).position().left;
            $('.header .gnb .gnb_bar').css({
                'width': gnbBarWidth,
                'left': gnbLiLeft,
            });
        }

        if (pageIndex == 11) {
            $('.content_tit_wrap').removeClass('on');
            $(".require_wrap").removeClass("on");
            $(".content_tit_wrap .page02_step").removeClass("on");
            $('.content_tit_wrap').css('width', $('.page-switch.page03_01').width());
            $('.header .gnb li').removeClass('current');
            $('.header .gnb li').eq(3).addClass("current");
            gnbBarWidth = $('.header .gnb li').eq(3).width();
            gnbLiLeft = $('.header .gnb li').eq(3).position().left;

            $('.header .gnb .gnb_bar').css({
                'width': gnbBarWidth,
                'left': gnbLiLeft,
            });
        }

        if (pageIndex == 12) {
            $('.content_tit_wrap').removeClass('on');
            $(".arrow_btn.next").removeClass("on");
            $(".require_wrap").removeClass("on");
            $(".content_tit_wrap .page02_step").removeClass("on");
            $('.content_tit_wrap').css('width', $('.page-switch.page04').width());
            $('.header .gnb li').removeClass('current');
            $('.header .gnb li').eq(3).addClass("current");
            gnbBarWidth = $('.header .gnb li').eq(3).width();
            gnbLiLeft = $('.header .gnb li').eq(3).position().left;

            $('.header .gnb .gnb_bar').css({
                'width': gnbBarWidth,
                'left': gnbLiLeft,
            });
        }

        $(".page-switch").removeClass("on");
        $(".page-switch").eq(pageIndex).addClass("on");
        $(".page02_step li").removeClass("current")
        $(".page02_step li").eq(pageIndex - 1).addClass('current');
        $(".page02_step li").eq(pageIndex - 1).prevAll().addClass('prev');
        $(".page02_step li").eq(pageIndex - 1).nextAll().removeClass('prev');


    }


    //tap menu

    $('.content .select_items .chk_item.purpose').click(function () {
        $(".page02_01").removeClass("height");
        $(".exp_wrap").show();
        $('.content .select_items .chk_item.purpose').removeClass('checked');
        $('.content .select_items .chk_item.purpose input').prop('checked', false);
        $(this).toggleClass('checked');
        $(this).find('input').prop('checked', true);

        // 예시 이미지 보여주기
        $('.exp_wrap .exp_items_wrap.purpose').removeClass('checked');
        $('.purpose.' + $(this).data('num')).addClass('checked');

        //선택에 따른 값 변화
        $('#purpose').text($(this).find('span').text());
        $('#checked_purpose').text($(this).find('span').text());

        $(".exp_wrap .purpose." + $(this).data('num')).addClass("on");
        $('.content .select_items .chk_item.purpose').removeClass('on');
        $(this).next().addClass("on");
        $(".page02_01 .select_items").removeClass("active");
    });


    $(".chk_item.purpose:nth-of-type(8)").click(function () {
        $(".page02_01").addClass("height");
    });
//
    $('.content .select_items .chk_item.battery').click(function () {
        $(".exp_wrap").show();
        $('.content .select_items .chk_item.battery').removeClass('checked');
        $('.content .select_items .chk_item.battery input').prop('checked', false);
        $(this).toggleClass('checked');
        $(this).find('input').prop('checked', true);

        // 예시 이미지 보여주기
        $('.exp_wrap .exp_items_wrap.battery').removeClass('checked');
        $('.battery.' + $(this).data('num')).addClass('checked');

        //선택에 따른 값 변화
        $('#battery').text($(this).find('span').text());
        $('#checked_battery').text($(this).find('span').text());

        $(".exp_wrap .battery." + $(this).data('num')).addClass("on");
        $('.content .select_items .chk_item.battery').removeClass('on');
        $(this).next().addClass("on");
        $(".page02_02 .select_items").removeClass("active");
    });

    $('.content .select_items .chk_item.battery_type').click(function () {
        $(".exp_wrap").show();
        $('.content .select_items .chk_item.battery_type').removeClass('checked');
        $('.content .select_items .chk_item.battery_type input').prop('checked', false);
        $(this).toggleClass('checked');
        $(this).find('input').prop('checked', true);

        // 예시 이미지 보여주기
        $('.exp_wrap .exp_items_wrap.battery_type').removeClass('checked');
        $('.battery_type.' + $(this).data('num')).addClass('checked');

        //선택에 따른 값 변화
        $('#battery_type').text($(this).find('span').text());
        $('#checked_battery_type').text($(this).find('span').text());

        $(".exp_wrap .battery_type." + $(this).data('num')).addClass("on");
        $('.content .select_items .chk_item.battery_type').removeClass('on');
        $(this).next().addClass("on");
    });

    $('.content .select_items .chk_item.voltage').change(function () {
        $('.content .select_items .chk_item.voltage').removeClass('checked');
        $('.content .select_items .chk_item.voltage input').prop('checked', false);
        $(this).toggleClass('checked');
        $(this).find('input').prop('checked', true);

        //선택에 따른 값 변화
        $('#voltage').text($(this).val());
    });

    $('.content .select_items .chk_item.volume').change(function () {
        $('.content .select_items .chk_item.volume').removeClass('checked');
        $('.content .select_items .chk_item.volume input').prop('checked', false);
        $(this).toggleClass('checked');
        $(this).find('input').prop('checked', true);

        //선택에 따른 값 변화
        $('#volume').text($(this).val());
        var selectVolume = $(this).val().replace("P","");
        var voltageStr = $('#voltage').text();
        var voltage = voltageStr.substring(voltageStr.indexOf("S")+1, voltageStr.lastIndexOf("V"));
        var ampere;

        $('#ampere').find('th').each((idx,obj) => {
            $('#cell_model').text() == obj.innerText ? ampere = $('#ah'+selectVolume).find("td:eq(" + idx +")").text() : "";
        })

        //운전가능시간 계산 (전력량 (Wh) ÷ 정격 출력 * 0.9)
        $('#info').text() != '모름' ? $('.volumeCalc').text(parseFloat(parseFloat(voltage) * parseFloat(ampere) / parseFloat($('#info').text()) * parseFloat(0.9)).toFixed(1)) : $('.volumeCalc').text("");
    });
    $('.content .select_items .chk_item.case').click(function () {
        $(".exp_wrap").show();
        $('.content .select_items .chk_item.case').removeClass('checked');
        $('.content .select_items .chk_item.case input').prop('checked', false);
        $(this).toggleClass('checked');
        $(this).find('input').prop('checked', true);

        // 예시 이미지 보여주기
        $('.exp_wrap .exp_items_wrap.case').removeClass('checked');
        $('.case.' + $(this).data('num')).addClass('checked');

        //선택에 따른 값 변화
        $('#case').text($(this).find('span').text());
        $('#checked_case').text($(this).find('span').text());

        $(".exp_wrap .case." + $(this).data('num')).addClass("on");
        $('.content .select_items .chk_item.case').removeClass('on');
        $(this).next().addClass("on");
    });
    $('input[name="sample_qty"]').change(function () {
        $('#qty').text($(this).val());
    })


    $(".chk_item.pcm:nth-of-type(5)").click(function () {
        $(".pcmN").addClass("open");
    });


    $('.content .select_items .chk_item.time').click(function () {
        $('.content .select_items .chk_item.time').removeClass('checked');
        $('.content .select_items .chk_item.time input').prop('checked', false);
        $(this).toggleClass('checked');
        $(this).find('input').prop('checked', true);

        // 예시 이미지 보여주기
        $('.exp_wrap .exp_items_wrap').removeClass('checked');
        $('.' + $(this).data('num')).addClass('checked');

        //선택에 따른 값 변화
        $('#time').text($(this).find('span').text());
    });

    function validationCheck() {

        let form = new FormData()

        switch (pageIndex) {
            case 0 :
                page_01 = false;
                const company = $('input[name="company"]').val();
                const name = $('input[name="name"]').val();
                const telNum = $('input[name="telNum"]').val();
                const email = $('input[name="email"]').val();
                const emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

                //업체명 이름 벨리데이션
                if (company == '' && name == '') {
                    $(".invalidCompany").css("display", "block");
                    return false;
                } else {
                    $(".invalidCompany").css("display", "none");
                }

                //전화번호 벨리데이션
                if (telNum == '') {
                    $(".invalidTelNum").css("display", "block");
                    return false;
                } else {
                    $(".invalidTelNum").css("display", "none");
                }

                //이메일 벨리데이션
                if (email == '') {
                    $(".invalidEmail").css("display", "block");
                    return false;
                } else {
                    $(".invalidEmail").css("display", "none");
                }

                if (telNum.length !== 11) {
                    alert("전화번호가 올바르지 않습니다.");
                    return false;
                } else if (telNum.includes("-")) {
                    alert("전화번호는 숫자만 입력해주세요.(ex. 01012341234)");
                    return false;
                } else if (!emailRule.test(email)) {
                    alert("이메일을 확인하시기 바랍니다.");
                    return false;
                }

                //개인정보처리 벨리데이션
                if ($('input[name="agree_chk"]').is(":checked")) {
                    $(".invalidAgree").css("display", "none");
                } else {
                    $(".invalidAgree").css("display", "block");
                    return false;
                }

                //견적번호 생성
                axios.post("/api/estimate", null, {
                    params : {
                        id : estimateNum,
                        company : company,
                        name : name,
                        tel : telNum,
                        email : email
                    }
                }).then((res) => {
                    if (res.status == 200) {
                        estimateNum = res.data;
                        sessionStorage.setItem("estimateNum", estimateNum);
                        page_01 = true;
                        $('#customNumber').text(estimateNum);
                        $('.tit').text("용도");
                        pageChange(page_01);
                    } else {
                        alert("오류가 발생했습니다. 다시 시도해 주세요.");
                    }
                })

                break;
            case 1 :
                page_02_01 = false;
                if (!$('input[name="items"]').is(":checked")) {
                    alert("용도를 선택해주세요");
                    return false;
                }

                form.append("id", estimateNum);
                form.append("purpose", $('#purpose').text());

                //데이터 저장
                axios.put("/api/estimate", form, {headers: {'Content-Type': 'multipart/form-data'}})
                    .then((res) => {
                        if (res.status == 200) {
                            page_02_01 = true;
                            $('.tit').text("출력");
                            pageChange(page_02_01);
                        } else {
                            alert("오류가 발생했습니다. 다시 시도해 주세요.");
                        }
                    })

                break;
            case 2 :
                page_02_01_01 = false;

                const ratedVolt = $('input[name="rated_volt"]').val();
                const maxVolt = $('input[name="max_volt"]').val();
                const unkownVolt = $('input[name="unknow_volt_chk"]').is(':checked');

                if (ratedVolt == '') {
                    if (!unkownVolt) {
                        $(".invalidVolt").css("display", "block");
                        return false;
                    } else {
                        $(".invalidVolt").css("display", "none");
                    }
                } else {
                    if (unkownVolt) {
                        alert("출력값과 모름은 동시에 선택할 수 없습니다.");
                        return false;
                    }
                }

                if(unkownVolt){
                    $('#info').text("모름");
                    form.append("rating", "모름");
                    form.append("maxRating", "");
                }else {
                    $('#info').text(ratedVolt + "Wh");
                    form.append("rating", ratedVolt);
                    form.append("maxRating", maxVolt);
                }

                form.append("id", estimateNum);

                //데이터 저장
                axios.put("/api/estimate", form, {headers: {'Content-Type': 'multipart/form-data'}})
                    .then((res) => {
                        if (res.status == 200) {
                            page_02_01_01 = true;
                            $('.tit').text("희망 배터리셀 종류");
                            pageChange(page_02_01_01);
                        } else {
                            alert("오류가 발생했습니다. 다시 시도해 주세요.");
                        }
                    })

                break;
            case 3 :
                page_02_02 = false;
                let batteryCell;
                if (!$('input[name="battery"]').is(":checked")) {
                    alert("배터리셀을 선택해주세요");
                    return false;
                }

                $('input:checkbox[name=battery]').each(function (index) {
                    if($(this).is(":checked")==true){
                        batteryCell = $(this).val();
                    }
                });

                form.append("id", estimateNum);
                form.append("batteryCell", batteryCell);

                //데이터 저장
                axios.put("/api/estimate", form, {headers: {'Content-Type': 'multipart/form-data'}})
                    .then((res) => {
                        if (res.status == 200) {
                            page_02_02 = true;

                            $('.items_wrap.cellChecked').empty();
                            $('.cell_table').empty();

                            axios.get("/api/cell_model", {
                                params : {
                                    category : batteryCell
                                }
                            }).then(res => {
                                if(res.status == 200){
                                    $('.cell_table').append("<tr class='tr'><th>항목</th></tr>");
                                    res.data.forEach((obj, index) => {
                                        $('.items_wrap.cellChecked').append("<div class='chk_item cell_model' data-num='tabCont0" + (parseInt(index) + parseInt(1)) + "' data-type='cell_model' onclick='clickCell(this)'>"
                                            + "<input type='checkbox' name='cell_model' value='"+ obj.name +"'><span>"+ obj.name +"</span></div>")
                                        $('.cell_table .tr').append("<th>" + obj.name + "</th>");
                                    })
                                    res.data[0].itemInfos.forEach((header, idx) => {
                                        $('.cell_table').append("<tr class='tr" + idx +"'><td class='cellTpoint'>" + header.type + "</td></tr>")
                                        res.data.forEach((obj, index) => {
                                            $('.cell_table .tr'+idx).append("<td>" + obj.itemInfos[idx].detail + "</td>");
                                        })
                                    })
                                }
                                $('.tit').text("셀 모델 선택");
                                pageChange(page_02_02);
                            })

                        } else {
                            alert("오류가 발생했습니다. 다시 시도해 주세요.");
                        }
                    })

                break;
            case 4 :
                page_02_03_01 = false;
                if (!$('input[name="cell_model"]').is(":checked")) {
                    alert("셀 모델을 선택해주세요");
                    return false;
                }

                form.append("id", estimateNum);
                form.append("cellModel", $('#cell_model').text());

                //데이터 저장
                axios.put("/api/estimate", form, {headers: {'Content-Type': 'multipart/form-data'}})
                    .then((res) => {
                        if (res.status == 200) {
                            page_02_03_01 = true;
                            $('.tit').text("전압 선택");
                            if($('#battery').text() == '리튬인산철 배터리') {
                                $('.voltage2').show();
                                $('.voltage1').hide();
                                $('.tbl_style02').show();
                                $('.tbl_style01').hide();
                            }else {
                                $('.voltage2').hide();
                                $('.voltage1').show();
                                $('.tbl_style02').hide();
                                $('.tbl_style01').show();
                            }
                            pageChange(page_02_03_01);
                        } else {
                            alert("오류가 발생했습니다. 다시 시도해 주세요.");
                        }
                    })

                break;
            case 5 :
                page_02_04 = false;
                console.log($('select[name="voltage"]').val());
                if($('select[name="voltage"]').val() == 0 ){
                    alert("전압을 선택해주세요");
                    return false;
                }

                form.append("id", estimateNum);
                form.append("voltage", $('#voltage').text());

                //데이터 저장
                axios.put("/api/estimate", form, {headers: {'Content-Type': 'multipart/form-data'}})
                    .then((res) => {
                        if (res.status == 200) {

                            axios.get("/api/cell_model", {
                                params : {
                                    category : $('#battery').text()
                                }
                            }).then(res => {
                                if(res.status == 200){
                                    $('#ampere').empty();
                                    $('#ampere').append("<tr class='tr'><th rowspan='3'>병렬수(p)</th></tr><tr id='model'></tr><tr id='point'></tr>");
                                    res.data.forEach((obj,idx) => {
                                        $('#model').append("<th>"+ obj.name +"</th>");
                                        $('#point').append("<td class='point'>(Ah)</td>")
                                    })
                                    for (let i = 1; i < 21; i++) {
                                        $('#ampere').append("<tr id='ah"+ i+ "'><td class='point'>"+ i +"</td></tr>");
                                    }
                                    res.data.forEach((obj,idx) => {
                                        for (let i = 1; i < 21; i++) {
                                            $('#ah'+i).append("<td>" + parseFloat((parseFloat(obj.itemInfos[1].detail) * parseFloat(0.001) * parseFloat(i)).toFixed(1)) +"</td>")
                                        }
                                    })

                                    page_02_04 = true;
                                    $('.tit').text("용량 선택");
                                    pageChange(page_02_04);
                                }
                            })
                        } else {
                            alert("오류가 발생했습니다. 다시 시도해 주세요.");
                        }
                    })

                break;
            case 6 :
                page_02_04_01 = false;
                if($('select[name="volume"]').val() == 0 ){
                    alert("용량을 선택해주세요");
                    return false;
                }

                form.append("id", estimateNum);
                form.append("ampere", $('#volume').text());

                //데이터 저장
                axios.put("/api/estimate", form, {headers: {'Content-Type': 'multipart/form-data'}})
                    .then((res) => {
                        if (res.status == 200) {

                            axios.get("/api/pcm", {
                                params : {
                                    category : $('#battery').text(),
                                    detail :  $('#voltage').text().substring(0, $('#voltage').text().indexOf("S")+1)
                                }
                            }).then(res => {
                                if(res.status == 200){
                                    $('#pcm_form').empty();
                                    $('#pcm_img').empty();
                                    res.data.forEach((obj,idx) => {
                                        $('#pcm_form').append("<div class='chk_item pcm' onclick='clickPcm(this)' data-num='tabCont0" + idx +"'><input type='checkbox' name='pcm' value='"+ obj.name +"'><span>"+ obj.name +"</span></div>");
                                        $('#pcm_img').append("<div class='exp_items_wrap pcm tabCont0"+ idx +"'>" +
                                            "                                <div class='exp_items'>" +
                                            "                                    <div class='item'>" +
                                            "                                        <p class='item_tit'>"+ obj.name +"</p>" +
                                            "                                        <div class='img_wrap'>" +
                                            "                                            <img class='pmc1' src='"+ obj.itemInfos[0].detail +"' alt='"+ obj.name +"'>" +
                                            "                                        </div>" +
                                            "                                    </div>" +
                                            "                                </div>" +
                                            "                            </div>")
                                    })

                                    page_02_04_01 = true;
                                    $('.tit').text("보호회로 (PCM) 선택");
                                    pageChange(page_02_04_01);
                                }
                            })
                        } else {
                            alert("오류가 발생했습니다. 다시 시도해 주세요.");
                        }
                    })

                break;
            case 7 :
                page_02_06 = false;
                if (!$('input[name="pcm"]').is(":checked")) {
                    alert("보호회로(PCM) 을 선택해주세요");
                    return false;
                }

                form.append("id", estimateNum);
                form.append("pcm", $('#pcm').text());
                form.append("pcmRemark", $('#pcmRemark').val());

                //데이터 저장
                axios.put("/api/estimate", form, {headers: {'Content-Type': 'multipart/form-data'}})
                    .then((res) => {
                        if (res.status == 200) {
                            page_02_06 = true;
                            $('.tit').text("케이스타입 선택");
                            pageChange(page_02_06);
                        } else {
                            alert("오류가 발생했습니다. 다시 시도해 주세요.");
                        }
                    })

                break;
            case 8 :
                page_02_06_01 = false;
                if (!$('input[name="case"]').is(":checked")) {
                    alert("케이스타입을 선택해주세요");
                    return false;
                }

                form.append("id", estimateNum);
                form.append("caseType", $('#case').text());
                form.append("caseRemark", $('#caseRemark').val());

                //데이터 저장
                axios.put("/api/estimate", form, {headers: {'Content-Type': 'multipart/form-data'}})
                    .then((res) => {
                        if (res.status == 200) {
                            page_02_06_01 = true;
                            $('.tit').text("주문 수량");
                            pageChange(page_02_06_01);
                        } else {
                            alert("오류가 발생했습니다. 다시 시도해 주세요.");
                        }
                    })

                break;
            case 9 :
                page_02_07 = false;
                if($('#sample_qty').val() == ""){
                    alert("주문수량을 입력해주세요");
                    return false;
                }

                if($('#basAddr').val() == ""){
                    alert("납품장소를 입력해주세요");
                    return false;
                }

                form.append("id", estimateNum);
                form.append("sampleQty", $('#sample_qty').val());
                form.append("sampleDate", $('#sample_date').val());
                form.append("address", $('#basAddr').val());
                form.append("addressDtl", + $('#dtlAddr').val());
                form.append("remark", $('#remark').val());

                for (var i = 0; i < filesArr.length; i++) {
                    // 삭제되지 않은 파일만 폼데이터에 담기
                    if (!filesArr[i].is_delete) {
                        form.append("attachFile", filesArr[i]);
                    }
                }

                //데이터 저장
                axios.put("/api/estimate", form, {headers: {'Content-Type': 'multipart/form-data'}})
                    .then((res) => {
                        if (res.status == 200) {

                            axios.get("/api/estimate", {
                                params : {
                                    id : estimateNum
                                }
                            }).then(res => {
                                if(res.status == 200){

                                    $('#est_company').text(res.data.company + " 귀하");
                                    $('#est_date').text(getCalender("today"));
                                    $('#est_tel').text(res.data.tel);
                                    $('#est_email').text(res.data.email);
                                    $('#est_num').text(res.data.id);
                                    $('#est_name').text(res.data.order.batteryCell + res.data.order.cellModel + res.data.order.voltage + res.data.order.ampere);
                                    $('#est_battery_qty').text(res.data.order.sampleQty);
                                    $('#est_pcm').text(res.data.order.pcm);
                                    $('#est_sample_date').text(res.data.order.sampleDate);
                                    $('#est_address').text(res.data.order.address + " " + res.data.order.addressDtl);
                                    $('#est_pcm_remark').text(res.data.order.pcmRemark);
                                    $('#est_case_remark').text(res.data.order.caseRemark);
                                    $('#est_remark').text(res.data.order.remark);
                                    $('#est_effective_date').text(getCalender("2weeks"));

                                    page_02_07 = true;
                                    $('.tit').text("견적서 확인");
                                    pageChange(page_02_07);

                                    /* 사파리에서 비동기처리 새창은 안뜬다. 고로 새창 -> 비동기실행 -> 새창주소이동 형태로 만들어줘야함 */
                                    // var pdfWindow = window.open();
                                    // pdfWindow.location = "/et/" + estimateNum;

                                    setTimeout(() => {
                                        pdfExport(res.data.company);
                                    }, 3000);
                                }
                            })
                        } else {
                            alert("오류가 발생했습니다. 다시 시도해 주세요.");
                        }
                    })
                break;
            case 10 :
                page_03 = true;
                $('.tit').text("기술상담 예약");
                pageChange(page_03);
                break;
            case 11 :
                page_03_01 = false;

                if(!$('input[name="reservation"]').is(':checked')){
                    alert("상담방법을 선택해주세요");
                    return false;
                }

                form.append("id", estimateNum);
                form.append("type", $('input[name="reservation"]:checked').val());
                form.append("typeDtl", $('input[name="reservation"]:checked').val() == "미팅요청" ? $('input[name="reservation_type"]:checked').val() : "");
                form.append("date", wijmo.Globalize.format(theCalendar.value,'yyyy-MM-dd'));
                form.append("time", $('input[name="time"]').is(":checked") == true ? $('input[name="time"]:checked').val() : "");

                //데이터 저장
                axios.post("/api/reservation", form, {headers: {'Content-Type': 'multipart/form-data'}})
                    .then((res) => {
                        if (res.status == 200) {
                            const bookingInfo = document.getElementById("booking_info");
                            $('#booking_info').empty();
                            let date = document.createElement('li');
                            date.innerHTML = "<span>예약날짜</span>" + res.data.date;
                            bookingInfo.append(date);
                            if(res.data.type != '') {
                                let type = document.createElement('li');
                                type.innerHTML = "<span>상담방법</span>" + res.data.type;
                                bookingInfo.append(type);
                            }
                            if(res.data.time != '') {
                                let time = document.createElement('li');
                                time.innerHTML = "<span>예약시간</span>" + res.data.time;
                                bookingInfo.append(time);
                            }
                            page_03_01 = true;
                            $('.tit').text("기술상담 예약완료");
                            pageChange(page_03_01);
                        } else {
                            alert("오류가 발생했습니다. 다시 시도해 주세요.");
                        }
                    })

                break;
        }
    }

    function prevValidationCheck() {
        switch (pageIndex) {
            case 1 :
                prevPageChange();
                break;
            case 2 :
                $('.tit').text("용도");
                prevPageChange();
                break;
            case 3 :
                $('.tit').text("출력");
                prevPageChange();
                break;
            case 4 :
                $('.tit').text("희망 배터리셀 종류");
                prevPageChange();
                break;
            case 5 :
                $('.tit').text("셀 모델 선택");
                prevPageChange();
                break;
            case 6 :
                $('.tit').text("전압 선택");
                prevPageChange();
                break;
            case 7 :
                $('.tit').text("용량 선택");
                prevPageChange();
                break;
            case 8 :
                $('.tit').text("보호회로 (PCM) 선택");
                prevPageChange();
                break;
            case 9 :
                $('.tit').text("케이스타입 선택");
                prevPageChange();
                break;
            case 10 :
                $('.tit').text("주문 수량");
                prevPageChange();
                break;
            case 11 :
                $('.tit').text("견적서 확인");
                prevPageChange();
                break;
            case 12 :
                $('.tit').text("기술상담 예약");
                prevPageChange();
                break;
        }
    }

    $(".change_btn").click(function () {
        prevValidationCheck();
    });

    //  $('.numeric-only').keyup(function(e){
    //     regNumber = /^[0-9]*$/;
    //     var str = $(this).val();
    //     // 정규표현식 활용하여 입력된 글자가 숫자가 아닐 경우, 해당 문자 삭제
    //     if(!regNumber.test(str)) {
    //         var res = str.substring(0, str.length-1);
    //         $(this).val(res);
    //     }
    // });

    // $(".page03_01 .select_items").hide();

    // $('#timeA').click(function(){
    //     $(".page03_01 .select_items").show();
    // });
    $('.chkTime:nth-of-type(2) > input').click(function () {
        $(".innerChk").addClass("on");
        // $(".page03_01 .select_items").addClass("on");
    });

    $('.chkTime:nth-of-type(1) > input').click(function () {
        // $(".page03_01 .select_items").removeClass("on");
        $(".innerChk").removeClass("on");
    });


    $("#next.arrow_btn").mouseover(function () {
        $("#next.arrow_btn > span").addClass("on");

    });

    $("#prev.arrow_btn").mouseover(function () {
        $("#prev.arrow_btn > span").addClass("on");

    });

    $("#next.arrow_btn").mouseout(function () {
        $("#next.arrow_btn > span").removeClass("on");

    });

    $("#prev.arrow_btn").mouseout(function () {
        $("#prev.arrow_btn > span").removeClass("on");

    });


    $('.page03_01 .chkTime:nth-of-type(1) > input').click(function(){
        $(".select_wrap .page03_01").css('height','64rem');
        $(".page03_01 .select_items .meet").removeClass('height');
        $(".page03_01 .select_items .call").removeClass('on');
        $(".page03_01 .select_items .call").addClass('on');

    });

    $('.page03_01 .chkTime:nth-of-type(2) > input').click(function(){
        $(".select_wrap .page03_01").css('height','115rem');
        $(".page03_01 .select_items .meet").removeClass('height');
        $(" .page03_01 .select_items .meet").addClass('height');
        $(".page03_01 .select_items .call").removeClass('on');
    });


    // $(".require_triger").click(function(){
    //     $(".content.pg_layout01 .arrow_btn.prev").removeClass("on");
    //     $(".content.pg_layout01 .arrow_btn.next").removeClass("on");
    // });


    var up = false;
    $(".require_triger").click(function () {
        if (up == false) {
            $(".content.pg_layout01 .arrow_btn.prev").removeClass("on");
            $(".content.pg_layout01 .arrow_btn.next").removeClass("on");
            up = true;
        } else {
            $(".content.pg_layout01 .arrow_btn.prev").addClass("on");
            $(".content.pg_layout01 .arrow_btn.next").addClass("on");
            up = false;
        }
    });


    $(".chk_item.purpose:nth-of-type(4)").click(function () {
        $(".content .select_items .chk_item.purpose.on").removeClass("add");
        $(".content .select_items .chk_item.purpose.on").addClass("add");
    });


}); // end

function saveData(form){
    axios.put("/api/estimate",form,{headers: {'Content-Type': 'multipart/form-data'}}).then((res) => {
        if (res.status == 200) {
            page_02_01_01 = true;
            $('.tit').text("희망 배터리셀 종류");
            pageChange(page_02_01_01);
        } else {
            alert("오류가 발생했습니다. 다시 시도해 주세요.");
        }
    })
}

function clickCell(_this){
    $('.content .select_items .chk_item.cell_model').removeClass('checked');
    $('.content .select_items .chk_item.cell_model input').prop('checked', false);
    $(_this).toggleClass("checked");
    $(_this).find('input').prop('checked', true);

    //선택에 따른 값 변화
    $('#cell_model').text($(_this).find('span').text());
    $('#checked_cell_model').text($(_this).find('span').text());

    $(".exp_wrap .cell_model." + $(_this).data('num')).addClass("on");
    $('.content .select_items .chk_item.cell_model').removeClass('on');
    $(_this).next().addClass("on");
}

function clickPcm(_this){
    $(".pcmN").removeClass("open");
    $(".exp_wrap").show();
    $('.content .select_items .chk_item.pcm').removeClass('checked');
    $('.content .select_items .chk_item.pcm input').prop('checked', false);
    $(_this).toggleClass('checked');
    $(_this).find('input').prop('checked', true);

    console.log("cc");

    // 예시 이미지 보여주기
    $('.exp_wrap .exp_items_wrap.pcm').removeClass('checked');
    $('.pcm.' + $(_this).data('num')).addClass('checked');

    //선택에 따른 값 변화
    $('#pcm').text($(_this).find('span').text());
    $('#checked_pcm').text($(_this).find('span').text());

    $(".exp_wrap .pcm." + $(_this).data('num')).addClass("on");
    $('.content .select_items .chk_item.pcm').removeClass('on');
    $(_this).next().addClass("on")
}

function findAddr(){
    var pop = window.open("/jusoPopup", "pop", "width=570, height=420, scrollbars=yes");
}
var jusoCallBack = function(roadFullAddr, addrDetail){
    document.getElementById("basAddr").value = roadFullAddr;
    document.getElementById("dtlAddr").value = addrDetail;
}

function pdfExport(name) {
    //저장 영역 div id
    html2canvas($('#pdf')[0],{
        //logging : true,		// 디버그 목적 로그
        //proxy: "html2canvasproxy.php",
        allowTaint : true,	// cross-origin allow
        useCORS: true,		// CORS 사용한 서버로부터 이미지 로드할 것인지 여부
        scale : 2			// 기본 96dpi에서 해상도를 두 배로 증가

    }).then(function(canvas) {
        // 캔버스를 이미지로 변환
        var imgData = canvas.toDataURL('image/png');

        var imgWidth = 190; // 이미지 가로 길이(mm) / A4 기준 210mm
        var pageHeight = imgWidth * 1.414;  // 출력 페이지 세로 길이 계산 A4 기준
        var imgHeight = canvas.height * imgWidth / canvas.width;
        var heightLeft = imgHeight;
        var margin = 10; // 출력 페이지 여백설정
        var doc = new jsPDF('p', 'mm');
        var position = 0;

        // 첫 페이지 출력
        doc.addImage(imgData, 'PNG', margin, position, imgWidth, imgHeight);
        heightLeft -= pageHeight;

        // 한 페이지 이상일 경우 루프 돌면서 출력
        while (heightLeft >= 20) {			// 35
            position = heightLeft - imgHeight;
            position = position - 28 ;		// -25

            doc.addPage();
            doc.addImage(imgData, 'PNG', margin, position, imgWidth, imgHeight);
            heightLeft -= pageHeight;
        }

        // 파일 저장
        doc.save(name + '님_견적서_.pdf');
    });

}

function getCalender(_type){
    var date = new Date();

    if(_type == "today"){
        var year = date.getFullYear();
        var month = ("0" + (1 + date.getMonth())).slice(-2);
        var day = ("0" + date.getDate()).slice(-2);

        return year + "-" + month + "-" + day;
    }else {
        date.setDate(date.getDate() + 14);

        var year = date.getFullYear();
        var month = ("0" + (1 + date.getMonth())).slice(-2);
        var day = ("0" + date.getDate()).slice(-2);

        return year + "-" + month + "-" + day;
    }
}

let fileNo = 0;
let filesArr = new Array();

/* 첨부파일 추가 */
function addFile(obj){
    const maxFileCnt = 5;   // 첨부파일 최대 개수
    const attFileCnt = document.querySelectorAll('.filebox').length;    // 기존 추가된 첨부파일 개수
    let remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
    const curFileCnt = obj.files.length;  // 현재 선택된 첨부파일 개수

    // 첨부파일 개수 확인
    if (curFileCnt > remainFileCnt) {
        alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
    }

    for (let i = 0; i < Math.min(curFileCnt, remainFileCnt); i++) {

        const file = obj.files[i];

        // 첨부파일 검증
        if (validation(file)) {
            // 파일 배열에 담기
            let reader = new FileReader();
            reader.onload = function () {
                filesArr.push(file);
            };
            reader.readAsDataURL(file)

            // 목록 추가
            let htmlData = '';
            htmlData += '<div id="file' + fileNo + '" class="filebox">';
            htmlData += '   <p class="name">' + file.name + '</p>';
            htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ');"><i class="far fa-minus-square"></i></a>';
            htmlData += '</div>';
            $('.fileList').append(htmlData);
            fileNo++;
        } else {
            continue;
        }
    }
    // 초기화
    document.querySelector("input[type=file]").value = "";
}

/* 첨부파일 검증 */
function validation(obj){
    const fileTypes = ['application/pdf', 'image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif', 'application/haansofthwp', 'application/x-hwp'];
    if (obj.name.length > 100) {
        alert("파일명이 100자 이상인 파일은 제외되었습니다.");
        return false;
    } else if (obj.size > (100 * 1024 * 1024)) {
        alert("최대 파일 용량인 100MB를 초과한 파일은 제외되었습니다.");
        return false;
    } else if (obj.name.lastIndexOf('.') == -1) {
        alert("확장자가 없는 파일은 제외되었습니다.");
        return false;
    } else if (!fileTypes.includes(obj.type)) {
        alert("첨부가 불가능한 파일은 제외되었습니다.");
        return false;
    } else {
        return true;
    }
}

/* 첨부파일 삭제 */
function deleteFile(num) {
    document.querySelector("#file" + num).remove();
    filesArr[num].is_delete = true;
}

/* 폼 전송 */
function submitForm() {
    // 폼데이터 담기
    var form = document.querySelector("form");
    var formData = new FormData(form);


    $.ajax({
        method: 'POST',
        url: '/register',
        dataType: 'json',
        data: formData,
        async: true,
        timeout: 30000,
        cache: false,
        headers: {'cache-control': 'no-cache', 'pragma': 'no-cache'},
        success: function () {
            alert("파일업로드 성공");
        },
        error: function (xhr, desc, err) {
            alert('에러가 발생 하였습니다.');
            return;
        }
    })
}



