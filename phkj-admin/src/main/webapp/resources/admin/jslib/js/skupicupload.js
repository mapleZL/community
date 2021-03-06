/**
 * 多文件上传插件
 * 1.依赖一下js文件
 *   jquery-1.8.2.min.js
 *   jquery.ui.widget.js
 *   jquery.iframe-transport.js
 *   jquery.fileupload.js
 * 2.默认的type=file的input控件的name为files
 * 3.options格式例子{"url":"http://www.test.com/upfiles","validate":{"fileSize":1024000,"fileType":"img"},"callback":"callbackfunctionname"}
 */
(function($) {
    $.fn.skuupload = function(options) {
        options = options || {};
        return this.each(function() {
            //get the options
            $target = $(this);
            var newoptions = {};
            var data = $.parseJSON($target.attr("multiparam"));
            if (data) {
                newoptions = $.extend(data, options);
            }

            if (!newoptions.url) {
                var $targetId = $target.attr("id");
                var $targetName = $target.attr("name");
                if (!$targetName) {
                    $targetName = $targetId;
                }
                window.console && console.log("url option is not provided in " + $targetName);
                return;
            }
            //append the required html
            $target.append($('<input>').attr('name', 'files').attr('type', 'file').attr('multiple', '').attr('data-url', newoptions.url).attr('class', 'upbtn'));

            //bind event
            var container = $target[0];
            $target.find("[name='files']").click(function() {
                $target.find("[name='notAllowedFiles']").find("span").remove();
            });

            if (newoptions.validate) {
                $("[name='files']", container).data("validate", newoptions.validate);
            }
            if (newoptions.callback) {
                $("[name='files']", container).data("callback", newoptions.callback);
            }

            $("[name='files']", container).fileupload({
                dataType: 'json',
                replaceFileInput: true,
                sequentialUploads: true,
                progressInterval: 50,
                bitrateInterval: 500,

                add: function(e, data) {
                    if (data.files && data.files.length == 1) {
                        var file = data.files[0];
                        var validate = $("[name='files']", container).data("validate");
                        if (validate) {
                            var suc = true;
                            var errMsg;
                            $.each(validate,
                            function(key, valueObj) {
                                var ret = $.fn.skuupload.validate[key].call(this, file, valueObj, container);
                                if (!ret) {
                                    suc = false;
                                    errMsg = valueObj.errMsg;
                                    return false; //break;
                                }
                            });
                            if (!suc) {
                                alert(errMsg);
                                return;
                            }
                        }

//                        var fileIndex = parseInt($("input[name='fileIndex']", container).val());
//                        $("input[name='fileIndex']", container).val(fileIndex + 1);
//                        data.context = fileIndex + 1;
//                        var lival = $('#prewtemplage').clone().attr('index', data.context).removeAttr('id').show();
//                        $(lival).find('img').attr('id', 'prev_'+data.context).attr('name','prev_'+data.context);
//                        $("#preview-img").append(lival);
//                        //$(container).parent().parent().find('.s-picnum').html((fileIndex + 1) + "/10");
                        data.submit();
                    }
                },
                done: function(e, data) {
                	if(data.result.success == false){
                		alert('无法连接图片服务器');
                	} else{
                		try {
							//图片回填
							if (data.result.data && data.result.data.attrid) {
								var thispicTr_ = $("#skuAttrTr_"
										+ data.result.data.attrid);
								var existspics_ = thispicTr_.find("img");
								if (existspics_.length > 0) {
									existspics_.parent().remove();
								}
								var pichtml_ = "<td><img style='max-width: 40px;' src='"
										+ imgdomain
										+ data.result.data.url
										+ "'/></td>";

								thispicTr_.append(pichtml_);
							}
							//调用回调函数
							var callback_ = $("[name='files']", container)
									.data("callback");
							if (callback_) {
								eval("(" + callback_
										+ "(data.result.data))");
							}
						} catch (e) {
							if(console){
								console.info(e);
							}
						}
                		
                	}
                },
                progress: function(e, data) {
                }
            });
        });
    };
    $.fn.skuupload.validate = {
        fileSize: function(currFile, valueObject, container) {
            var limitedFileSize = valueObject.value;
            if (currFile > limitedFileSize) {
                return false;
            }
            return true;
        },
        fileType: function(currFile, valueObject, container) {
            var limitedFileType = valueObject.value;
            if (!currFile.type) {
                $("[name='notAllowedFiles']", container).append($('<span>').html('文件[' + currFile.name + ']格式不正确'));
                return false;
            }
            var acceptFileTypes = $.fn.skuupload.validate.fileTypeReg[limitedFileType].call(this);
            if (acceptFileTypes) {
                if (currFile.type && !acceptFileTypes.test(currFile.type)) {
                    $("[name='notAllowedFiles']", container).append($('<span>').html('文件[' + currFile.name + ']格式不正确'));
                    return false;
                }
            }
            return true;
        },
        fileMaxNum: function(currFile, valueObject, container) {
            var maxNum = valueObject.value;
            var uploaded = parseInt($("input[name='fileIndex']", container).val());
            if (uploaded && (uploaded + 1) > parseInt(maxNum)) {
                return false;
            }
            return true;
        }
    };
    $.fn.skuupload.validate.fileTypeReg = {
        img: function() {
            return /^image\/(gif|jpe?g|png)$/i;
        }
    };
})(jQuery);