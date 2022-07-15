// cms 공통 스트립트 입니다 
// 해당 스크립트는 페이지의 원활한 이해를 돕기 위한 참고용으로 봐주세요
// 아이템 추가 클릭시 모달창 뜸
$(".topMenu4 > p").click(function(){
    $(".graybg").css("display","block");
    $(".modal").css("display","block");
});

//모달창 X 누르면 모달창 없어지기
$(".modal > h1 > span").click(function(){
    $(".graybg").css("display","none");
    $(".modal").css("display","none");
});


// 팝업
$(function(){
  $('.popup_trigger').on('click',function(e){

      var trigger_type = $(this).attr("data-trigger");

      $('.popup[data-modal="'+trigger_type+'"]').addClass("is_on")
      e.preventDefault();
  });
  $('.popup_close').on('click',function(e){
      $('.popup').removeClass("is_on")
      e.preventDefault();
  });
});

function _getFormatDate(date, type) {
    if(type == 'm'){
        var year = date.getFullYear();
        var month = ("0" + (1 + date.getMonth())).slice(-2);

        return year + "-" + month;
    }else{
        var year = date.getFullYear();
        var month = ("0" + (1 + date.getMonth())).slice(-2);
        var day = ("0" + date.getDate()).slice(-2);

        return year + "-" + month + "-" + day;
    }
}

//페이지 클릭이벤트
function clickPager(idx, grid, gridId) {
    grid.collectionView.moveToPage(idx - 1); // 그리드 0부터 시작
    refreshPaging(grid.collectionView.totalItemCount, idx, grid, gridId); // 그리드의 전체 아이템 수, 클릭한 인덱스 값 넘겨주기
}

//페이징 html 셋팅
function refreshPaging(totalData, currentPage, grid, gridId, gridView, gridPager) {
    //페이지 사이즈
    const dataPerPage = grid.collectionView.pageSize; // 그리드의 한 페이지당 보여지는 행의 개수
    // 페이지 숫자 목록
    const pageCount = 5;
    //전체 페이지
    const totalPage = Math.ceil(totalData / dataPerPage);
    //페이지그룹
    const pageGroup = Math.ceil(currentPage / pageCount);

    let last = pageGroup * pageCount; // 가장 마지막 인덱스

    if (last > totalPage) {
        last = totalPage;
    }

    let first = last - (pageCount - 1);

    const next = last + 1; // 다음
    var prev = first - 1; // 이전

    if (totalPage < 1) {
        first = last;
    }

    const pages = $('#' + gridId + 'Pager');
    pages.empty();

    // <<  <
    pages.append('<span onClick="clickPager(1, ' + gridId + ', ' + "'" + gridId + "'" + ')" > << </span>');
    if (first > pageCount) {
        pages.append('<span onClick="clickPager(' + prev + ', ' + gridId + ', ' + "'" + gridId + "'" + ')" > ' + '<' + ' </span>');
    } else {
        pages.append('<span onClick="clickPager(1, ' + gridId + ', ' + "'" + gridId + "'" + ')" > ' + '<' + ' </span>');
    }

    // 현재 페이지 인덱스 만큼 append
    for (let j = first; j <= last; j++) {
        if (currentPage === j) {
            pages.append('<span class="selectPage" id="' + gridId + 'paging_' + j + '" onClick="clickPager(' + j + ', ' + gridId + ', ' + "'" + gridId + "'" + ')" > ' + j + ' </span>');

        } else if (j > 0) {
            pages.append('<span id="' + gridId + 'paging_' + j + '" onClick="clickPager(' + j + ', ' + gridId + ', ' + "'" + gridId + "'" + ')" > ' + j + ' </span>');

        }
    }

    // >  >>
    if (next > pageCount && next < totalPage) {
        pages.append('<span onClick="clickPager(' + next + ', ' + gridId + ', ' + "'" + gridId + "'" + ')" >  ' + '>' + ' </span>');
    } else {
        pages.append('<span onClick="clickPager(' + totalPage + ', ' + gridId + ', ' + "'" + gridId + "'" + ')" >  ' + '>' + ' </span>');
    }
    pages.append('<span onClick="clickPager(' + totalPage + ', ' + gridId + ', ' + "'" + gridId + "'" + ')" > >> </span>');


//    var selectBox = '<select name="'+gridId+'pageCount" id="'+gridId+'pageCount" onchange="pagingCountChange( monGrid, monView, monGridPager)">'
//    	//'<select name="'+gridId+'pageCount" id="'+gridId+'pageCount" onchange="pagingCountChange('+grid+', '+gridView+', '+gridPager+')">'
//				    +'<option value="30">30</option>'
//				    +'<option value="50">50</option>'
//				    +'<option value="100" selected="selected">100</option>'
//				    +'</select>';
//
//    pages.append(selectBox);

    $(".pager").removeClass('wj-control wj-content wj-pager wj-collectionview-navigator wj-state-empty wj-state-readonly');
}

function pagingCountChange(grid, gridView, gridPager){
    console.log("pagingCountChange");

    gridView = new wijmo.collections.CollectionView(result, {
        pageSize: 100,
        trackChanges: true
    });
    gridPager.cv = gridView;
    grid.itemsSource = gridView;
}

//그리드 초기 레이아웃 복원
function _resetUserGridLayout(layoutId, grid, initColumns) {

    grid.columns.clear();
    initColumns.forEach((col) => {
        grid.columns.push(new wijmo.grid.Column(col));
    });

    localStorage.setItem(layoutId, grid.columnLayout);
    alert("컬컴위치를 초기화하였습니다.");
    _setUserGridLayout(layoutId, grid, initColumns);
}

function sessionCheck(staffId){
    if(staffId=="null" || staffId == null){
        alert("세션이 종료되어 로그인화면으로 이동합니다.");
        location.href = "/cms";
        return false;

    }else{
        return true;

    }
}

//그리드 레이아웃 저장
function _getUserGridLayout(layoutId, grid) {
    localStorage.setItem(layoutId, grid.columnLayout);
    alert("컬럼위치를 저장하였습니다.");
}

//그리드 레이아웃 복원
function _setUserGridLayout(layoutId, grid, initColumns) {
    /*  // 주석 소스처럼 진행하여도 컬럼위치는 복원되나, cellTemplate 설정이 저장되지않음.
    	var layout = localStorage.getItem(layoutId);
        if (layout) {
        	grid.columnLayout = layout;
        }
    */

    if (window.localStorage[layoutId]) {
        let columnsArr = JSON.parse(window.localStorage[layoutId]).columns;

        grid.columns.clear();
        columnsArr.forEach((col) => {
            initColumns.forEach((col2) => {
                if (col.binding == col2.binding) {
                    grid.columns.push(new wijmo.grid.Column(col2));
                }
            });
        });
    }
}

// 팝업
$(function(){
    $('.popup_close').on('click',function(e){
        $('.popup').removeClass("is_on")
        e.preventDefault();
    });
});

// 탭 메뉴
$(document).ready(function() {
  $(".tabpanel").hide(); 
  $(".tabpanel:first").show();
  $('.tablist > a').click(function(){
    $('.tablist > a').removeClass('on');
    $(this).addClass('on');
    var tab_id = $(this).attr('data-id');
    $("#" + tab_id).show();
    $("#" + tab_id).siblings().hide();
  })
});





