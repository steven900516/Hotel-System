// $(function () {
//     $.ajax({
//         url: '/hotel/queryHotel',
//         type: 'post',
//         dataType: 'json',
//         data: {
//             hotelTranslatedName: unescape($.cookie("hotelName"))
//         },
//         success: function (res) {
//             console.log(res)
//             if(res.data.records.length == 0){
//                 layer.msg('无结果！请在更换一个酒店名字', {
//                     icon: 5,
//                     time: 2000,
//                     anim: 1,
//                 });
//             }else{
//                 console.log(res)
//                 // window.location = "/search_result"
//             }
//         }
//     })
// })