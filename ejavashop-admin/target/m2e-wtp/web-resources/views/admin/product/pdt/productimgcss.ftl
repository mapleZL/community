    <style>
        /*预览图片*/
        #previewImgBox{
            height:150px;
            padding:10px 0px 10px 83px;
        }
        .preview-img{
            height:150px;
        }
        .preview-img li{
            list-style: none;
            width:150px;
            height:150px;
            float:left;
            padding-right:20px;
        }
        .preview-img .img{
            position: relative;
            /* float:left;*/
            width:150px;
            height:150px;
        }

        .upload-img{
            padding-left:83px;
            padding-top:10px;
        }
        .img-box{
            display: none;
            position: absolute;
            left:0;
            top:0;
            width: 100%;
            height: 100%;
            color: white;
            font-style: 16px;
            background: rgba(47, 47, 47, 0.5);
            cursor: pointer;
        }
        .del-img{
            position: relative;
            top:72px;
            left:57px;
            color:#fff;
            cursor: pointer;
        }
        /*end*/
        .chars{
        	margin: 10px 30px;
        }
        
        .chars label{
        	padding: 5px 12px;
			font-size: 20px;
			cursor: pointer;
        }
        
        .chars label:hover{
        	background-color: #abe0e9;
        }
        
        .cur-info{
	        font-size: 13px;
			margin: 0 40px;
			width: 85px;
			background: #eafff2;
			text-align: center;
        }
        
        .nowh{
        	font-size: 13px;
			margin: 0px 10px;
        }
        
        .nowh + a{
        	color: blue;
        }
        
        .panel-fit{
			height: 100%;
			margin: 0;
			padding: 0;
			border: 0;
		    overflow: inherit;
		}
	</style>