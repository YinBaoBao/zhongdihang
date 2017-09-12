var DispatchBankInfo =
    {
        bankInfoData: {},
        ztreeInstance: null,
        validateFields: {}
    };

/**
 * 清除数据
 */
DispatchBankInfo.clearData = function () {
    this.bankInfoData = {};
};



DispatchBankInfo.init = function ()
{
    DispatchBankInfo.clearData;
    var id = Bank.getQueryString("id");
    if (id)
    {
        var dispatch;
        var ajax = new $ax
        (
            Bank.api.dispatch_findbyid, function (data)

            {
                dispatch = data;
            }, function (dispatch)
            {
                Bank.error("查询不到此银行记录！");
            }
        );
        ajax.set("dispatchId", Bank.getQueryString("id"));
        ajax.start();
        DispatchBankInfo.guarantyHtml(dispatch)
        var bankVm = new Vue
        ({
            el: '#borrow-info',
            data:
                {
                    dispatch: dispatch
                }
        });
    }
}

DispatchBankInfo.guarantyHtml = function (data)
{
    var guarantyList = new Array();
    var dispatchTrack = new Array();
    guarantyList = data.guarantyDtoList;
    dispatchTrack = data.dispatchTrackDtoList;
    var hu = new Vue
    ({
        el: '#guarantyhtml',
        data:
            {
                guarantyList: guarantyList
            }
    });
    var hu = new Vue
    ({
        el: '#dispatchTrack',
        data:
            {
                dispatchTrackDtoList: dispatchTrack
            }
    });
    var hu = new Vue
    ({
        el: '#dispatchtrackl',
        data:
            {
                dispatchTrackDtoList: dispatchTrack
            }
    });
}



$(function ()
{
    DispatchBankInfo.init();
    Bank.initValidator("borrow-info", DispatchBankInfo.validateFields);
});