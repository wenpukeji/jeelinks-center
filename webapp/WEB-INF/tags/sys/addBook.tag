<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<%@attribute name="ownedBookIds" description="已选的bookIds" %>
<%@ attribute name="delBtn" type="java.lang.String" required="false" description="清除按钮文字表述" %>
<%@ attribute name="selectMultiple" type="java.lang.Boolean" required="false" description="是否允许多选"%>
<%@ attribute name="confirmCallback" type="java.lang.String" required="true" description="确认时回调"%>

<a href="javascript:" onclick="finderOpen();" class="btn">设置书籍</a>
<script type="text/javascript">
	function finderOpen(){
		var url = "${ctx}/study/book/chooseBook";
		jBox("<div id='BookContent'  ></div>" ,
            {
                title: "选择书籍",
                width: 1000, height: 500,
                buttons:{'确定':true,'关闭': false},
                submit:function(c){
                    var bookids = '';
                    $('#BookContent'+' input[name=oldBookId]').each(function(o,i){
                        bookids+=$(this).val()+',';
                    });
                    $('#BookContent'+' input[name=bookid]:checked').each(function(o,i){
                        bookids+=$(this).val()+',';
                    });

                    ${confirmCallback}(bookids);
                }
            });


        var loadCallback = function(){
            $("#BookContent").find('div.pagination a').bind('click',function(e){
                if($(this).is('[data-page]')){
                    $("#BookContent").load(url+'?pageNo='+$(this).data('page')+'&pageSize='+$("#BookContent").find('div[data-size]').data('size') , <c:if test="${not empty ownedBookIds  }">{bookids:'${ownedBookIds}' },</c:if> loadCallback);
                }
                return false;
            });
        };
        $("#BookContent").load(url , <c:if test="${not empty ownedBookIds  }">{bookids:'${ownedBookIds}' },</c:if> loadCallback);
	}
</script>