function fileSelected() {
    //alert(fileContainer);
    document.getElementById("guide").style.display = "none";
    var file = document.getElementById('file' + array[fileNum]).files[0];
    if (!file) return;
    fileNum++;
    filelist.innerHTML += "<button disabled='false' class='filebtn' id='button" + (fileNum - 1) + "' onclick='viewPdf(\"file_pdfjs_test.pdf\")'>" + file.name + "</button>"+
    "<button class='remove'>��</button><br>";


    fileContainer.innerHTML += "<input type=\"file\" name=\"file" + array[fileNum] + " id=\"file" + array[fileNum] + "\"" +
    "accept=\"application/pdf, application/vnd.ms-excel, application/msword\"" +
    "onchange=\"fileSelected();\" style=\"display: none\"/>";
    //alert(fileContainer.innerHTML);
    buttonContainer.innerHTML = "<button style=\"margin-left: 0px;width: 220px;height: 40px\" class=\"yellow-button\"" +
    "id=\"selectFile\" type=\"button\" onclick=\"file" + fileNum + ".click();\"" +
    "value=\"ѡ���ļ�\"><b>ѡ&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��</b></button>";
    //alert("��ѡ�����ļ�");
    //var file = document.getElementById('fileToUpload').files[0];
    //if (file) {
    //    var fileSize = 0;
    //    if (file.size > 1024 * 1024)
    //        fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
    //    else
    //        fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
    //    document.getElementById('fileSize').innerHTML = '��С: ' + fileSize;
    //    var type;
    //    switch (file.type) {
    //        case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
    //            type = "EXCEL";
    //        case "application/msword":
    //            type = "WORD";
    //        case "application/pdf":
    //            type = "PDF";
    //        default :
    //            type = "δ֪";
    //    }
    //    document.getElementById('fileType').innerHTML = '�ļ�����: ' + type;
    //    document.getElementById('up-btn').style.display = "block";
    //}
}