<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'InsertUser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
		    //新建或编辑 保存提交  
    function submitPic(){  
        if(!$("#picForm").form('validate')){  
            return false;  
        }  
        var projectId = $("#projectId").val();  
        var name=$("#picName").val();  
        var type="1";//展示图片  
        var f = $("#file").val();  
        if(f==null||f==""){  
            $("#picTip").html("<span style='color:Red'>错误提示:上传文件不能为空,请重新选择文件</span>");  
            return false;  
          }else{  
           var extname = f.substring(f.lastIndexOf(".")+1,f.length);  
           extname = extname.toLowerCase();//处理了大小写  
           if(extname!= "jpeg"&&extname!= "jpg"&&extname!= "gif"&&extname!= "png"){  
             $("#picTip").html("<span style='color:Red'>错误提示:格式不正确,支持的图片格式为：JPEG、GIF、PNG！</span>");  
             return false;  
            }  
          }  
         var file = document.getElementById("file").files;    
         var size = file[0].size;  
         if(size>2097152){  
              $("#picTip").html("<span style='color:Red'>错误提示:所选择的图片太大，图片大小最多支持2M!</span>");   
              return false;  
          }  
        ajaxFileUploadPic(projectId,name,type);  
    }  
      
    function ajaxFileUploadPic(projectId,name,type) {  
        $.ajaxFileUpload({  
            //url : '${ctx}/projectPic/saveorupdate.jhtml?projectId='+projectId+'&name='+name+'&type='+type, //用于文件上传的服务器端请求地址  
            url:'/springMVC/upload/upload?projectId='+projectId+'&name='+name+'&type='+type, 
            secureuri : false, //一般设置为false  
            fileElementId : 'file', //文件上传空间的id属性  <input type="file" id="file" name="file" />  
            type : 'post',  
            dataType : 'json', //返回值类型 一般设置为json  
            success : function(data, status) //服务器成功响应处理函数  
            {  
                 $("#picList").datagrid('reload');  
                 $('#uploadPicWindow').window('close');  
                // alert(data.msg);  
            },  
            error : function(data, status, e)//服务器响应失败处理函数  
            {  
                 $("#picList").datagrid('reload');  
                 $('#uploadPicWindow').window('close');  
                // alert(data.msg);  
            }  
        });  
        return false;  
    }
    //ajax
    	    jQuery.extend({  
          
        createUploadIframe: function(id, uri)  
        {  
                //create frame  
                var frameId = 'jUploadFrame' + id;  
                var iframeHtml = '<iframe id="' + frameId + '" name="' + frameId + '" style="position:absolute; top:-9999px; left:-9999px"';  
                if(window.ActiveXObject)  
                {  
                    if(typeof uri== 'boolean'){  
                        iframeHtml += ' src="' + 'javascript:false' + '"';  
      
                    }  
                    else if(typeof uri== 'string'){  
                        iframeHtml += ' src="' + uri + '"';  
      
                    }     
                }  
                iframeHtml += ' />';  
                jQuery(iframeHtml).appendTo(document.body);  
      
                return jQuery('#' + frameId).get(0);              
        },  
        createUploadForm: function(id, fileElementId, data)  
        {  
            //create form     
            var formId = 'jUploadForm' + id;  
            var fileId = 'jUploadFile' + id;  
            var form = jQuery('<form  action="" method="POST" name="' + formId + '" id="' + formId + '" enctype="multipart/form-data"></form>');      
            if(data)  
            {  
                for(var i in data)  
                {  
                    jQuery('<input type="hidden" name="' + i + '" value="' + data[i] + '" />').appendTo(form);  
                }             
            }         
            var oldElement = jQuery('#' + fileElementId);  
            var newElement = jQuery(oldElement).clone();  
            jQuery(oldElement).attr('id', fileId);  
            jQuery(oldElement).before(newElement);  
            jQuery(oldElement).appendTo(form);  
      
      
              
            //set attributes  
            jQuery(form).css('position', 'absolute');  
            jQuery(form).css('top', '-1200px');  
            jQuery(form).css('left', '-1200px');  
            jQuery(form).appendTo('body');        
            return form;  
        },  
      
        ajaxFileUpload: function(s) {  
            // TODO introduce global settings, allowing the client to modify them for all requests, not only timeout          
            s = jQuery.extend({}, jQuery.ajaxSettings, s);  
            var id = new Date().getTime()          
            var form = jQuery.createUploadForm(id, s.fileElementId, (typeof(s.data)=='undefined'?false:s.data));  
            var io = jQuery.createUploadIframe(id, s.secureuri);  
            var frameId = 'jUploadFrame' + id;  
            var formId = 'jUploadForm' + id;          
            // Watch for a new set of requests  
            if ( s.global && ! jQuery.active++ )  
            {  
                jQuery.event.trigger( "ajaxStart" );  
            }              
            var requestDone = false;  
            // Create the request object  
            var xml = {}     
            if ( s.global )  
                jQuery.event.trigger("ajaxSend", [xml, s]);  
            // Wait for a response to come back  
            var uploadCallback = function(isTimeout)  
            {             
                var io = document.getElementById(frameId);  
                try   
                {                 
                    if(io.contentWindow)  
                    {  
                         xml.responseText = io.contentWindow.document.body?io.contentWindow.document.body.innerHTML:null;  
                         xml.responseXML = io.contentWindow.document.XMLDocument?io.contentWindow.document.XMLDocument:io.contentWindow.document;  
                           
                    }else if(io.contentDocument)  
                    {  
                         xml.responseText = io.contentDocument.document.body?io.contentDocument.document.body.innerHTML:null;  
                        xml.responseXML = io.contentDocument.document.XMLDocument?io.contentDocument.document.XMLDocument:io.contentDocument.document;  
                    }                         
                }catch(e)  
                {  
                    jQuery.handleError(s, xml, null, e);  
                }  
                if ( xml || isTimeout == "timeout")   
                {                 
                    requestDone = true;  
                    var status;  
                    try {  
                        status = isTimeout != "timeout" ? "success" : "error";  
                        // Make sure that the request was successful or notmodified  
                        if ( status != "error" )  
                        {  
                            // process the data (runs the xml through httpData regardless of callback)  
                            var data = jQuery.uploadHttpData( xml, s.dataType );      
                            // If a local callback was specified, fire it and pass it the data  
                            if ( s.success )  
                                s.success( data, status );  
          
                            // Fire the global callback  
                            if( s.global )  
                                jQuery.event.trigger( "ajaxSuccess", [xml, s] );  
                        } else  
                            jQuery.handleError(s, xml, status);  
                    } catch(e)   
                    {  
                        status = "error";  
                        jQuery.handleError(s, xml, status, e);  
                    }  
      
                    // The request was completed  
                    if( s.global )  
                        jQuery.event.trigger( "ajaxComplete", [xml, s] );  
      
                    // Handle the global AJAX counter  
                    if ( s.global && ! --jQuery.active )  
                        jQuery.event.trigger( "ajaxStop" );  
      
                    // Process result  
                    if ( s.complete )  
                        s.complete(xml, status);  
      
                    jQuery(io).unbind()  
      
                    setTimeout(function()  
                                        {   try   
                                            {  
                                                jQuery(io).remove();  
                                                jQuery(form).remove();    
                                                  
                                            } catch(e)   
                                            {  
                                                jQuery.handleError(s, xml, null, e);  
                                            }                                     
      
                                        }, 100)  
      
                    xml = null  
      
                }  
            }  
            // Timeout checker  
            if ( s.timeout > 0 )   
            {  
                setTimeout(function(){  
                    // Check to see if the request is still happening  
                    if( !requestDone ) uploadCallback( "timeout" );  
                }, s.timeout);  
            }  
            try   
            {  
      
                var form = jQuery('#' + formId);  
                jQuery(form).attr('action', s.url);  
                jQuery(form).attr('method', 'POST');  
                jQuery(form).attr('target', frameId);  
                if(form.encoding)  
                {  
                    jQuery(form).attr('encoding', 'multipart/form-data');                 
                }  
                else  
                {     
                    jQuery(form).attr('enctype', 'multipart/form-data');              
                }             
                jQuery(form).submit();  
      
            } catch(e)   
            {             
                jQuery.handleError(s, xml, null, e);  
            }  
              
            jQuery('#' + frameId).load(uploadCallback   );  
            return {abort: function () {}};   
      
        },  
      
        uploadHttpData: function( r, type ) {  
            var data = !type;  
            data = type == "xml" || data ? r.responseXML : r.responseText;  
            // If the type is "script", eval it in global context  
            if ( type == "script" )  
                jQuery.globalEval( data );  
            // Get the JavaScript object, if JSON is used.  
            if ( type == "json" )  
                eval( "data = " + data );  
            // evaluate scripts within html  
            if ( type == "html" )  
                jQuery("<div>").html(data).evalScripts();  
      
            return data;  
        },  
        handleError: function( s, xhr, status, e )      {  
            // If a local callback was specified, fire it  
                    if ( s.error ) {  
                        s.error.call( s.context || s, xhr, status, e );  
                    }  
      
                    // Fire the global callback  
                    if ( s.global ) {  
                        (s.context ? jQuery(s.context) : jQuery.event).trigger( "ajaxError", [xhr, s, e] );  
                    }  
        }  
       
    });    
	</script>
  </head>
 
  <body>
      <!-- 上传窗口 -->   
        <div id="uploadPicWindow" class="easyui-window" title="上传图片"  style="width:420px;height:220px;padding:20px;background:#fafafa;" data-options="iconCls:'icon-save',closable:true, collapsible:true,minimizable:true,maximizable:true">  
            <form id="picForm" action="" method="post">  
                <div style="margin-bottom:20px">  
                    图片名称:  
                    <input type="text" name="name" id="picName" class="easyui-textbox" style="width:80%" data-options="required:true,validType:'stringCheck'"/>  
                </div>  
                <br/>  
                <div style="margin-bottom:20px">  
                    选择图片:  
                    <input type="file" name="file" id="file" data-options="prompt:'Choose a file...'" style="width:80%"/>  
                </div>  
                <div id="picTip"></div>  
                <div id="formWindowfooterPic1" style="padding:5px;text-align:right;">   
                    <a href="#" onclick="submitPic();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提交</a>  
                </div>  
            </form>  
        </div>  
  </body>
</html>
