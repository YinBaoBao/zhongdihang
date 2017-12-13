<template>
  <div class="application">
    <div class="app_table">
      <ul class="header">
        <li>
          <span class="title" style="width: 66px;">登记类型:</span>
          <el-select @change="_djlxchange" v-model="djlx.djlxmc" placeholder="请选择"
                     style="flex: 1;">
            <el-option
              v-for="item in djlxoptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </li>
        <li>
          <span class="title" style="width: 80px;">登记子类型:</span>
          <el-select @change="_djzlxchange" v-model="djlx.djzlxmc" placeholder="请选择"
                     style="flex: 1;">
            <el-option
              v-for="item in djzlxoptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </li>
        <li>
          <span class="title" style="width:66px;">报件编号:</span>
          <input class="ipt_text" v-model="$store.state.Bjbh" type="text" value="" readonly="readonly"
                 placeholder="" style="flex: 1;">
        </li>
        <!--<li>-->
        <!--<span class="title" style="width:80px;">经办人姓名:</span>-->
        <!--<input class="ipt_text" type="text" value="" readonly="readonly" placeholder="">-->
        <!--</li>-->
      </ul>
      <div class="proposer">
        <div class="inner">
          <table class="table">
            <tr>
              <th colspan="7" style="min-width: 100px;text-align: left">
                <img class="img_point" src="./red.png" alt="red">
                <span style="vertical-align: middle;">申请人情况</span>
                <el-popover
                  ref="popover"
                  placement="right"
                  width="600"
                  trigger="click">
                  <div style="padding: 0 10px;">
                    <div style="height: 40px;line-height: 40px;">
                      <h2 style="font-size: 16px;font-weight: 500;padding-left: 4px;">申请人信息须知</h2>
                    </div>
                    <el-table :data="gridData">
                      <el-table-column property="djlx" label="登记类型"></el-table-column>
                      <el-table-column property="qlr" label="权利人需填写内容"></el-table-column>
                      <el-table-column property="ywr" label="义务人需填写内容"></el-table-column>
                    </el-table>
                    <div style="height: 40px;line-height: 40px;">
                      <span style="font-size: 14px;">注: “转移登记（合并）”为转移合并抵押的登记类型。</span>
                    </div>
                  </div>
                </el-popover>
                <span class="el-icon-information infor" v-popover:popover></span>
              </th>
            </tr>
            <tr>
              <td style="min-width: 100px;"></td>
              <td>姓名/名称</td>
              <td>证件类型</td>
              <td>证件号</td>
              <td>联系地址</td>
              <td>电话</td>
              <td>操作</td>
            </tr>
            <tr v-for="(item,index) in proposer.qlrs">
              <td>权利人</td>
              <td class="td_text" style="padding: 0;">
                <input v-model="item.qlrmc" class="input_text" type="text" readonly="readonly" placeholder=""
                       value="">
              </td>
              <td class="td_text" style="padding: 0;">
                <input v-model="item.qlrzjzlmc" class="input_text" type="text" readonly="readonly" placeholder=""
                       value="">
              </td>
              <td class="td_text" style="padding: 0;">
                <input v-model="item.qlrzjh" class="input_text" type="text" readonly="readonly" placeholder=""
                       value="">
              </td>
              <td class="td_text" style="padding: 0;">
                <input v-model="item.qlrdz" class="input_text" type="text" readonly="readonly" placeholder=""
                       value="">
              </td>
              <td class="td_text" style="padding: 0;">
                <input v-model="item.qlrdh" class="input_text" type="text" readonly="readonly" placeholder=""
                       value="">
              </td>
              <td class="sqrqk_caozuo" style="padding:0;min-width: 120px;">
                <a href="javascript:;" @click="_application_add('权利人',proposer.qlrs)"
                   style="margin-right: 2px;" title="添加"><i class="el-icon-plus"></i></a>
                <a href="javascript:;" @click="_edit_sqrqk(index,item,'权利人')"
                   style="margin-right: 2px;" title="修改"><i class="el-icon-edit"></i></a>
                <a href="javascript:;" @click="_delete_sqrqk(index,'权利人')" title="删除">
                  <i class="el-icon-delete2"></i></a>
              </td>
            </tr>
            <tr v-if="proposer.qlrdlr">
              <td>权利人代理人</td>
              <td class="td_text" style="padding: 0;">
                <input v-model="proposer.qlrdlr.qlrdlrmc" class="input_text" type="text" readonly="readonly"
                       placeholder="" value="">
              </td>
              <td class="td_text" style="padding: 0;">
                <input v-model="proposer.qlrdlr.qlrdlrzjzlmc" class="input_text" type="text" readonly="readonly"
                       placeholder="" value="">
              </td>
              <td class="td_text" style="padding: 0;">
                <input v-model="proposer.qlrdlr.qlrdlrzjh" class="input_text" type="text" readonly="readonly"
                       placeholder="" value="">
              </td>
              <td class="td_text" style="padding: 0;">
                <input v-model="proposer.qlrdlr.qlrdlrdz" class="input_text" type="text" readonly="readonly"
                       placeholder="" value="">
              </td>
              <td class="td_text" style="padding: 0;">
                <input v-model="proposer.qlrdlr.qlrdlrdh" class="input_text" type="text" readonly="readonly"
                       placeholder="" value="">
              </td>
              <td class="sqrqk_caozuo" style="padding:0;">
                <a href="javascript:;" @click="_add_dlr('权利人代理人')"
                   style="margin-right: 2px;" title="添加"><i class="el-icon-plus"></i></a>
                <a href="javascript:;" @click="_edit_sqrqkdlr(proposer.qlrdlr,'权利人代理人')"
                   style="margin-right: 2px;" title="修改"><i class="el-icon-edit"></i></a>
                <a href="javascript:;" @click="_clerar_sqrqkdlr(proposer.qlrdlr, '权利人代理人')" title="删除"><i
                  class="el-icon-delete2"></i></a>
              </td>
            </tr>
            <tr v-for="(item,index) in proposer.ywrs">
              <td>义务人</td>
              <td class="td_text" style="padding: 0;">
                <input v-model="item.ywrmc" class="input_text" type="text" readonly="readonly" placeholder=""
                       value="">
              </td>
              <td class="td_text" style="padding: 0;">
                <input v-model="item.ywrzjzlmc" class="input_text" type="text" readonly="readonly" placeholder=""
                       value="">
              </td>
              <td class="td_text" style="padding: 0;">
                <input v-model="item.ywrzjh" class="input_text" type="text" readonly="readonly" placeholder=""
                       value="">
              </td>
              <td class="td_text" style="padding: 0;">
                <input v-model="item.ywrdz" class="input_text" type="text" readonly="readonly" placeholder=""
                       value="">
              </td>
              <td class="td_text" style="padding: 0;">
                <input v-model="item.ywrdh" class="input_text" type="text" readonly="readonly" placeholder=""
                       value="">
              </td>
              <td class="sqrqk_caozuo">
                <a href="javascript:;" @click="_application_add('义务人',proposer.ywrs)"
                   style="margin-right: 2px;" title="添加"><i class="el-icon-plus"></i></a>
                <a href="javascript:;" @click="_edit_sqrqk(index,item,'义务人')"
                   style="margin-right: 2px;" title="修改"><i class="el-icon-edit"></i></a>
                <a href="javascript:;" @click="_delete_sqrqk(index,'义务人')" title="删除"><i
                  class="el-icon-delete2"></i></a>
              </td>
            </tr>
            <tr v-if="proposer.ywrdlr">
              <td>义务人代理人</td>
              <td class="td_text" style="padding: 0;">
                <input v-model="proposer.ywrdlr.ywrdlrmc" class="input_text" type="text" readonly="readonly"
                       placeholder="" value="">
              </td>
              <td class="td_text" style="padding: 0;">
                <input v-model="proposer.ywrdlr.ywrdlrzjzlmc" class="input_text" type="text" readonly="readonly"
                       placeholder="" value="">
              </td>
              <td class="td_text" style="padding: 0;">
                <input v-model="proposer.ywrdlr.ywrdlrzjh" class="input_text" type="text" readonly="readonly"
                       placeholder="" value="">
              </td>
              <td class="td_text" style="padding: 0;">
                <input v-model="proposer.ywrdlr.ywrdlrdz" class="input_text" type="text" readonly="readonly"
                       placeholder="" value="">
              </td>
              <td class="td_text" style="padding: 0;">
                <input v-model="proposer.ywrdlr.ywrdlrdh" class="input_text" type="text" readonly="readonly"
                       placeholder="" value="">
              </td>
              <td class="sqrqk_caozuo">
                <a href="javascript:;" @click="_add_dlr('义务人代理人')"
                   style="margin-right: 2px;" title="添加"><i class="el-icon-plus"></i></a>
                <a href="javascript:;" @click="_edit_sqrqkdlr(proposer.ywrdlr, '义务人代理人')" style="margin-right: 2px;"
                   title="修改"><i class="el-icon-edit"></i></a>
                <a href="javascript:;" @click="_clerar_sqrqkdlr(proposer.ywrdlr,'义务人代理人')"><i class="el-icon-delete2"
                                                                                              title="删除"></i></a>
              </td>
            </tr>
          </table>
        </div>
      </div>
      <div class="mortgage">
        <table class="table">
          <tr>
            <th colspan="6" style="min-width: 100px;text-align: left;">
              <img class="img_point" src="./blue.png" alt="blue">
              <span style="vertical-align: middle;">不动产情况</span>
            </th>
          </tr>
          <tr>
            <td>坐落</td>
            <td colspan="4" style="padding: 0;">
              <table style="width: 100%;">
                <tr>
                  <td class="td_text" style="padding: 0;border: none;">
                    <input v-model="bdcqk.zl" class="input_text" type="text" placeholder="" value="">
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td colspan="" style="min-width: 164px;">
              原不动产权证书号
              <el-popover
                ref="popover"
                placement="right"
                width="400"
                trigger="click">
                <div style="padding: 15px 10px">
                  <p style="padding-bottom: 8px;font-size: 14px;line-height:18px;">
                    原证为不动产证时填写不动产证号，否则填写原房产证号和原土地证号，中间用“，”区分。
                  </p>
                  <p style="padding-bottom: 8px;font-size: 14px;line-height:18px;">
                    不动产证号举例：“苏（2017）苏州市不动产权第8019801号”;
                  </p>
                  <p style="padding-bottom: 8px;font-size: 14px;line-height:18px;">
                    原房产证号和原土地证号举例：“苏房权证市区字第10106630号，苏国用（2004）第02008490号”。
                  </p>
                </div>
              </el-popover>
              <span class="el-icon-information infor" v-popover:popover></span>
            </td>
            <td class="td_text" style="padding: 0;">
              <input v-model="bdcqk.bdcqzshy" class="input_text" type="text" placeholder="" value="">
            </td>
            <td style="min-width: 164px;">不动产单元号</td>
            <td class="td_text" style="padding: 0;">
              <input v-model="bdcqk.bdcdyh" class="input_text" type="text" placeholder="" value="">
            </td>
          </tr>
          <tr>
            <td colspan="">房屋用途</td>
            <td class="td_text" style="padding: 0;">
              <el-select @change="_fwytchange" v-model="bdcqk.fwytmc" placeholder="请选择"
                         style="width: 100%;margin-left: -1px;">
                <el-option
                  v-for="item in fwytoptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </td>
            <td>房屋面积</td>
            <td class="td_text" style="padding: 0;">
              <input v-model="bdcqk.fwmj" class="input_text" type="text" placeholder="" value="">
            </td>
          </tr>
          <tr>
            <td colspan="">土地用途</td>
            <td class="td_text" style="padding: 0;">
              <el-select @change="_tdytchange" v-model="bdcqk.tdytmc" placeholder="请选择"
                         style="width: 100%;margin-left: -1px;">
                <el-option
                  v-for="item in tdytoptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </td>
            <td>土地面积</td>
            <td class="td_text" style="padding: 0;">
              <input v-model="bdcqk.tdmj" class="input_text" type="text" placeholder="" value="">
            </td>
          </tr>
          <tr>
            <td style="min-width: 100px;">土地使用起始日</td>
            <td class="td_text" style="padding: 0;">
              <el-date-picker
                v-model="bdcqk.tdsyqssj"
                type="date"
                placeholder="选择日期"
                :picker-options="pickerOptions"
                @change="_tdsyqssjtime"
                style="width: 100%; margin-left: -1px;">
              </el-date-picker>
            </td>
            <td style="min-width: 100px;">土地使用结束日</td>
            <td class="td_text" style="padding: 0;">
              <el-date-picker
                v-model="bdcqk.tdsyjssj"
                type="date"
                placeholder="选择日期"
                :picker-options="pickerOptions"
                @change="_tdsyjssjtime"
                style="width: 100%; margin-left: -1px;">
              </el-date-picker>
            </td>
          </tr>
          <tr>
            <td style="min-width: 58px;">权利性质</td>
            <td class="td_text" style="padding: 0;">
              <el-select @change="_qlxzchange" v-model="bdcqk.qlxzmc" placeholder="请选择"
                         style="width: 100%;margin-left: -1px;">
                <el-option
                  v-for="item in qlxzoptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </td>
            <td style="min-width: 58px;">所属区县</td>
            <td class="td_text" style="padding: 0;border-right: none;">
              <el-select @change="_ssqxchange" v-model="bdcqk.ssqxmc" placeholder="请选择"
                         style="width: 100%;margin-left: -1px;">
                <el-option
                  v-for="item in ssqxoptions1"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </td>
          </tr>
        </table>
      </div>
      <div class="mortgage">
        <table class="table">
          <tr>
            <th colspan="6" style="min-width: 100px;text-align: left;">
              <img class="img_point" src="./orange.png" alt="orange">
              <span style="vertical-align: middle;">抵押情况</span>
            </th>
          </tr>
          <tr>
            <td style="min-width: 116px;">被担保债权数额</td>
            <td class="td_text" style="padding: 0;">
              <input v-model="mortgage.bdbzqse" value="万元" class="input_text" type="text" placeholder="万元">
            </td>
            <td style="min-width: 116px;">抵押合同签署日期</td>
            <td class="td_text" style="padding: 0;">
              <el-date-picker
                v-model="mortgage.dyhtqdrq"
                type="date"
                format="yyyy-MM-dd"
                placeholder="选择日期"
                :picker-options="pickerOptions"
                @change="_dyhtqdrqtime"
                style="width: 100%;margin-left: -1px;">
              </el-date-picker>
            </td>
            <td style="min-width: 100px;">银行抵押业务号</td>
            <td class="td_text" style="padding: 0;min-width: 146px;">
              <input v-model="mortgage.yhdyywh" class="input_text" type="text" placeholder="">
            </td>
          </tr>
          <tr>
            <td colspan="">债务履行开始时间</td>
            <td class="td_text" style="padding: 0;">
              <el-date-picker
                v-model="mortgage.zwlxqssj"
                type="date"
                format="yyyy-MM-dd"
                placeholder="选择日期"
                :picker-options="pickerOptions"
                @change="_zwlxqstime"
                style="width: 100%;margin-left: -1px;">
              </el-date-picker>
            </td>
            <td>债务履行结束时间</td>
            <td class="td_text" style="padding: 0;">
              <el-date-picker
                v-model="mortgage.zwlxjssj"
                type="date"
                placeholder="选择日期"
                :picker-options="pickerOptions"
                @change="_zwlxjstime"
                style="width: 100%;margin-left: -1px;">
              </el-date-picker>
            </td>
            <td colspan="">贷款业务编号</td>
            <td class="td_text" style="padding: 0;">
              <input v-model="mortgage.dkywbh" class="input_text" type="text" placeholder="">
            </td>
          </tr>
          <tr>
            <td colspan="6" style="padding: 0;">
              <table class="table_1" cellpadding="10" cellspacing="0">
                <tr>
                  <td style="min-width: 100px;">不动产评估价格</td>
                  <td class="td_text" style="padding: 0;">
                    <input v-model="mortgage.bdcpgjg" class="input_text" type="text" placeholder="万元">
                  </td>
                  <td style="min-width: 90px;">土地评估价格</td>
                  <td class="td_text" style="padding: 0;">
                    <input v-model="mortgage.tdpgjg" class="input_text" type="text" placeholder="万元">
                  </td>
                  <td style="min-width: 86px;">房产评估价格</td>
                  <td class="td_text" style="padding: 0;">
                    <input v-model="mortgage.fcpgjg" class="input_text" type="text"
                           placeholder="万元">
                  </td>
                  <td>贷款账号</td>
                  <td class="td_text" style="padding: 0;border-right: none;">
                    <input v-model="mortgage.dkzh" class="input_text" type="text" placeholder="">
                  </td>
                </tr>
                <tr>
                  <td>担保范围</td>
                  <td class="td_text" style="padding: 0;">
                    <input v-model="mortgage.dbfw" class="input_text" type="text"
                           placeholder="详见合同">
                  </td>
                  <td style="min-width: 126px;">在建建筑物抵押范围</td>
                  <td class="td_text" style="padding: 0;">
                    <input v-model="mortgage.zjjzwdyfw" class="input_text" type="text"
                           placeholder="万元">
                  </td>
                  <td>房产抵押面积</td>
                  <td class="td_text" style="padding: 0;">
                    <input v-model="mortgage.fcdymj" class="input_text" type="text"
                           placeholder="平方米">
                  </td>
                  <td style="min-width: 86px;">土地抵押面积</td>
                  <td class="td_text" style="padding: 0;border-right: none;">
                    <input v-model="mortgage.tddymj" class="input_text" type="text"
                           placeholder="平方米">
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td colspan="8" style="padding: 0;">
              <table class="table_2" cellpadding="10" cellspacing="0" style="width: 100%;">
                <tr>
                  <td>抵押方式</td>
                  <td class="td_text" style="padding: 0;">
                    <el-select @change="_dyfschange" v-model="mortgage.dyfsmc" placeholder="请选择"
                               style="width:100%;margin-left: -1px;">
                      <el-option
                        v-for="item in dyfsoptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </td>
                  <td>债权人</td>
                  <td class="td_text" style="padding: 0;">
                    <input v-model="mortgage.zqr" class="input_text" type="text" placeholder=""
                           value="中国建设银行股份有限公司苏州分行">
                  </td>
                  <td>债务人</td>
                  <td class="td_text" style="padding: 0;border-right: none;">
                    <input v-model="mortgage.zwr" class="input_text" type="text" placeholder=""
                           value="">
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </div>
    </div>
    <div class="app_list">
      <list @appLook="Look_reg" @apply_submit="_apply_submit" @newcreateapply="_newcreateapply"
            @Print="_printapply" :upDatalist="upDatalist" :bjblztmc="bjblztmc"></list>
    </div>
    <div class="dialog">
      <el-dialog :title="typename" :visible.sync="typeVisible" size="000">
        <div style="width: 370px;">
          <label>登记类型 :</label>
          <el-cascader :options="Typeoption"
                       @active-item-change="handleItemChange"
                       @change="_checked"
                       v-model="selectdjlx"
                       :props="props"
                       style="width: 260px;margin: 12px 12px 30px 12px">
          </el-cascader>
        </div>
        <div style="width: 370px;">
          <label>所属区县 :</label>
          <el-select @change="_Quxchange" v-model="ssqxvalue" placeholder="请选择"
                     style="width: 260px;margin: 0 12px 30px 12px">
            <el-option
              v-for="item in ssqxoptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button @click="typeVisible = false">取 消</el-button>
          <el-button type="primary" @click="_typeoption_submit">确 定</el-button>
        </div>
      </el-dialog>
      <el-dialog title="登记申请书" :visible.sync="regVisible" size="000" top="10%">
        <Register v-if="regVisible" :Propose="proposer" :mortgage="mortgage" :bdcqk="bdcqk" :xwsx="xwsx"
                  :bjbh="bjbh" ref="register"></Register>
        <span slot="footer" class="dialog-footer">
            <el-button :plain="true" type="success" style="padding: 8px 36px;"
                       @click="regVisible = false">关 闭</el-button>
            <el-button :plain="true" type="success" style="padding: 8px 36px;" @click="_print">打印</el-button>
          </span>
      </el-dialog>
      <el-dialog :title="handTitle" :visible.sync="handVisible" size="000" top="10%" :close-on-click-modal="false">
        <handsontable v-if="handVisible" :Handdata="Handdata" :Title="Titlestate" :bjbh="bjbh"
                      ref=Keephand></handsontable>
        <span slot="footer" class="dialog-footer">
            <el-button @click="Close">取 消</el-button>
            <el-button type="primary" @click="_Keephand">完 成</el-button>
          </span>
      </el-dialog>
      <el-dialog :title="handTitle" :visible.sync="add_dlr" size="000" top="6%" :modal="false"
                 :close-on-click-modal="false">
        <el-form :model="AddForm" :rules="rules" ref="AddForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="姓名或名称" prop="username">
            <el-input v-model="AddForm.username"></el-input>
          </el-form-item>
          <el-form-item label="证件类型" prop="zjlxvalue">
            <el-select v-model="AddForm.zjlxvalue" placeholder="请选择" @change="_zjlxchange">
              <el-option
                v-for="item in zjlx"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="证件号" prop="zjh">
            <el-input v-model="AddForm.zjh"></el-input>
          </el-form-item>
          <el-form-item label="联系地址" prop="address">
            <el-input v-model="AddForm.address"></el-input>
          </el-form-item>
          <el-form-item label="手机号码" prop="telephone">
            <el-input v-model="AddForm.telephone"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="add_dlr = false">关 闭</el-button>
          <el-button type="primary" @click="_add_qlr_submit('AddForm')">保 存</el-button>
        </span>
      </el-dialog>
      <el-dialog :title="handTitle" :visible.sync="add_sqrqk" size="000" top="6%" :modal="false"
                 :close-on-click-modal="false">
        <el-form :model="SqrqkForm" :rules="Sqrqkrules" ref="SqrqkForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="申请人性质" prop="applyvalue">
            <el-select v-model="SqrqkForm.applyvalue" :disabled="Disablel" placeholder="请选择" @change="_qlrzlchange">
              <el-option
                v-for="item in Qlrzl"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="权利人类型" prop="qlrlxvalue">
            <el-select v-model="SqrqkForm.qlrlxvalue" placeholder="请选择" @change="_qlrlxchange">
              <el-option
                v-for="item in Qlrlx"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="姓名或名称" prop="username">
            <el-input v-model="SqrqkForm.username"></el-input>
          </el-form-item>
          <el-form-item label="证件类型" prop="zjlxvalue">
            <el-select v-model="SqrqkForm.zjlxvalue" placeholder="请选择" @change="_zjlxchange">
              <el-option
                v-for="item in zjlx"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="证件号" prop="zjh">
            <el-input v-model="SqrqkForm.zjh"></el-input>
          </el-form-item>
          <el-form-item label="联系地址" prop="address">
            <el-input v-model="SqrqkForm.address"></el-input>
          </el-form-item>
          <el-form-item label="手机号码" prop="telephone">
            <el-input v-model="SqrqkForm.telephone"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="add_sqrqk = false">关 闭</el-button>
          <el-button type="primary" @click="_application_add_submit('SqrqkForm')">保 存</el-button>
        </span>
      </el-dialog>
      <el-dialog :title="handTitle" :visible.sync="edit_sqrqk" size="000" top="6%" :modal="false"
                 :close-on-click-modal="false">
        <el-form :model="SqrqkForm" :rules="Sqrqkrules" ref="SqrqkForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="申请人性质" prop="applyvalue">
            <el-select v-model="SqrqkForm.applyvalue" :disabled="Disablel" placeholder="请选择" @change="_qlrzlchange">
              <el-option
                v-for="item in Qlrzl"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="申请人类型" prop="qlrlxvalue">
            <el-select v-model="SqrqkForm.qlrlxvalue" placeholder="请选择" @change="_qlrlxchange">
              <el-option
                v-for="item in Qlrlx"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="姓名或名称" prop="username">
            <el-input v-model="SqrqkForm.username"></el-input>
          </el-form-item>
          <el-form-item label="证件类型" prop="zjlxvalue">
            <el-select v-model="SqrqkForm.zjlxvalue" placeholder="请选择" @change="_zjlxchange">
              <el-option
                v-for="item in zjlx"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="证件号" prop="zjh">
            <el-input v-model="SqrqkForm.zjh"></el-input>
          </el-form-item>
          <el-form-item label="联系地址" prop="address">
            <el-input v-model="SqrqkForm.address"></el-input>
          </el-form-item>
          <el-form-item label="手机号码" prop="telephone">
            <el-input v-model="SqrqkForm.telephone"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="edit_sqrqk = false">关 闭</el-button>
            <el-button type="primary" @click="_edit_sqrqk_submit('SqrqkForm')">保 存</el-button>
          </span>
      </el-dialog>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import list from '../list/list.vue';
  import Register from '../../components/dialog/Register.vue';
  import handsontable from '../../components/dialog/handsontable.vue';
  import {formatDate} from '../../common/js/date.js';
  export default {
    data() {
      let zjhvalidata = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('证件号不能为空'));
        }
        if (this.zjlxvalidator === '身份证') {
          let city = {
            11: '北京',
            12: '天津',
            13: '河北',
            14: '山西',
            15: '内蒙古',
            21: '辽宁',
            22: '吉林',
            23: '黑龙江',
            31: '上海',
            32: '江苏',
            33: '浙江',
            34: '安徽',
            35: '福建',
            36: '江西',
            37: '山东',
            41: '河南',
            42: '湖北 ',
            43: '湖南',
            44: '广东',
            45: '广西',
            46: '海南',
            50: '重庆',
            51: '四川',
            52: '贵州',
            53: '云南',
            54: '西藏 ',
            61: '陕西',
            62: '甘肃',
            63: '青海',
            64: '宁夏',
            65: '新疆',
            71: '台湾',
            81: '香港',
            82: '澳门',
            91: '国外 '
          };
          if (!/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(value)) {
            return callback(new Error('身份证号格式错误'));
          }
          if (!city[value.substr(0, 2)]) {
            return callback(new Error('地址编码错误'));
          }
          if (value.length === 18) {   // 18位身份证需要验证最后一位校验位
            let code = value.split('');
            // ∑(ai×Wi)(mod 11)
            // 加权因子
            let factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
            // 校验位
            let parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
            let sum = 0;
            let ai = 0;
            let wi = 0;
            for (let i = 0; i < 17; i++) {
              ai = code[i];
              wi = factor[i];
              sum += ai * wi;
            }
            if (parity[sum % 11] + '' !== value[17]) {
              return callback(new Error('校验位错误'));
            }
          }
        }
        return callback();
      };
      return {
        typeVisible: false,    // 级联选择
        checkeVsibke: true,    // 是否选择级联选项
        regVisible: false,     //  查看登记申请书
        handVisible: false,    //  申请人情况添加
        handTitle: '添加权利人',          //  添加权利人
        Handdata: '',           // 添加权利人对应数据
        Titlestate: '',          //  添加人名情况
        Disablel: false,         // 禁止选项
        add_sqrqk: false,
        edit_sqrqk: false,
        Editindex: '',
        pickerOptions: {
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '昨天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周前',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
        },
        Typeoption: [  // 三级级选项
          {
            label: '转移登记',
            cities: [
              {
                label: '商品房买卖转移登记（合并）',
                code: ''
              }
            ],
            code: ''
          },
          {
            label: '变更登记',
            cities: [],
            code: ''
          },
          {
            label: '抵押登记',
            cities: [],
            code: ''
          }
        ],
        selectdjlx: [],  // 弹框登记类型选项value值
        props: {
          value: 'label',
          children: 'cities'
        },
        dyfsoptions: [
          {
            value: '一般抵押',
            code: '1'
          }, {
            value: '最高额抵押',
            code: '2'
          }
        ],
        dyfscode: '',
        fwytoptions: [
          {
            value: '',
            code: ''
          }, {
            value: '',
            code: ''
          }
        ],
        fwytcode: '',
        tdytoptions: [
          {
            value: '',
            code: ''
          }, {
            value: '',
            code: ''
          }
        ],
        tdytcode: '',
        qlxzoptions: [
          {
            value: '',
            label: '',
            code: ''
          }, {
            value: '',
            label: '',
            code: ''
          }
        ],
        qlxzcode: '',
        ssqxoptions: [
          {
            value: '',
            label: '',
            code: ''
          }, {
            value: '',
            label: '',
            code: ''
          }
        ],
        ssqxoptions1: [
          {
            value: '',
            label: '',
            code: ''
          }, {
            value: '',
            label: '',
            code: ''
          }
        ],
        ssqxcode: '',
        ssqxvalue: '',
        djlxoptions: [
          {
            value: '抵押登记',
            label: '抵押登记',
            code: '910'
          }
        ],
        djlxcode: '',
        djzlxoptions: [
          {
            value: '',
            label: '',
            code: ''
          }, {
            value: '',
            label: '',
            code: ''
          }
        ],
        djzlxcode: '',
        djzlxvalue: '',
        application: {},
        djlx: {
          djlx: '',
          djlxmc: '',
          djzlx: '',
          djzlxmc: ''
        },
        bjbh: '', // 报件编号
        proposer: {
          qlrs: [
            {
              bjbh: '',
              qlrunid: '',
              qlrxh: 1,
              qlrzl: 2,
              qlrzlmc: '',
              qlrlx: 2,
              qlrlxmc: '',
              qlrmc: '',
              qlrzjzl: 7,
              qlrzjzlmc: '',
              qlrzjh: '',
              qlrdh: '',
              qlrdz: ''
            }
          ],
          ywrs: [
            {
              bjbh: '',
              ywrdh: '',
              ywrdz: '',
              ywrlx: '',
              ywrlxmc: '',
              ywrmc: '',
              ywrunid: '',
              ywrxh: '',
              ywrzjh: '',
              ywrzjzl: '',
              ywrzjzlmc: '',
              ywrzl: '',
              ywrzlmc: ''
            }
          ],
          qlrdlr: {
            qlrdlrdh: '',
            qlrdlrdz: '',
            qlrdlrmc: '',
            qlrdlrzjh: '',
            qlrdlrzjzl: '',
            qlrdlrzjzlmc: ''
          },
          ywrdlr: {
            ywrdlrdh: '',
            ywrdlrdz: '',
            ywrdlrmc: '',
            ywrdlrzjh: '',
            ywrdlrzjzl: '',
            ywrdlrzjzlmc: ''
          }
        },
        mortgage: {  // 抵押情况
          dyfs: '',
          dyfsmc: '',
          bdcpgjg: '',
          dbfw: '',
          dkywbh: '',
          dkzh: '',
          dyhtqdrq: '',
          fcdymj: '',
          fcpgjg: '',
          tddymj: '',
          tdpgjg: '',
          yhdyywh: '',
          bdbzqse: '',
          zjjzwdyfw: '',
          zqr: '',
          zwlxjssj: '',
          zwlxqssj: '',
          zwr: ''
        },
        bdcqk: {    //  不动产情况
          bdcqzshy: '',
          bdcqzshx: '',
          bdcdyh: '',
          zl: '',
          ssqx: '',
          ssqxmc: '',
          fwyt: '',
          fwytmc: '',
          fwmj: '',
          tdyt: '',
          tdytmc: '',
          tdmj: '',
          tdsyqssj: '',
          tdsyjssj: '',
          qlxz: '',
          qlxzmc: ''
        },
        xwsx: {
          afgyfeqk: '',
          gyfs: '',
          gyfsmc: '',
          qtsm: '',
          sffbcz: '',
          sffbczmc: '',
          sfgy: '',
          sfgymc: ''
        },
        upDatalist: [
          {
            bjbh: '',
            mlxh: '',
            mlmc: '',
            mlwjlx: '',
            mlwjsl: '',
            state: ''
          }
        ],  // 需要上的资料
        selectcode: { // 登记类型编号
          djlx: '',
          djzlx: '',
          ssqx: ''
        },
        typename: '请选择登记类型',  // 登记类型
        add_dlr: false, // 添加代理人
        AddForm: {
          username: '',
          zjlxvalue: '',
          zjh: '',
          address: '',
          telephone: ''
        },
        zjlx: [
          {
            code: '',
            value: '身份证'
          },
          {
            code: '',
            value: '营业执照'
          }
        ],
        zjlxvalidator: '',
        qlrzjlxcode: '',
        ywrzjlxcode: '',
        rules: {
          username: [
            {required: true, message: '请输入姓名或名称', trigger: 'change'}
          ],
          zjlxvalue: [
            {required: true, message: '请选择证件类型', trigger: 'change'}
          ],
          zjh: [
            {required: true, validator: zjhvalidata, trigger: 'blur,change'}
          ],
          address: [
            {required: true, message: '请输入地址', trigger: 'change'}
          ],
          telephone: [
            {required: true, message: '请输入手机号', trigger: 'change'},
            {type: 'string', message: '请输入正确的手机号码', pattern: /^1[0-9]{10}$/, trigger: 'blur,change'}
          ]
        },
        SqrqkForm: {
          applyvalue: '',
          username: '',
          qlrlxvalue: '',
          zjlxvalue: '',
          zjh: '',
          address: '',
          telephone: ''
        },
        Qlrlx: [
          {
            code: '',
            value: ''
          }
        ],
        Qlrzl: [
          {
            code: '',
            value: ''
          }
        ],
        Qlrlxcode: '',
        Qlrzlcode: '',
        Qlrzjlxcode: '',
        Ywrzjlxcode: '',
        qlrxh: '',
        Sqrqkrules: {
          applyvalue: [
            {required: true, message: '请选择申请人', trigger: 'change'}
          ],
          qlrlxvalue: [
            {required: true, message: '请选择权利人类型', trigger: 'change'}
          ],
          username: [
            {required: true, message: '请输入姓名或名称', trigger: 'change'}
          ],
          zjlxvalue: [
            {required: true, message: '请选择证件类型', trigger: 'change'}
          ],
          zjh: [
            {required: true, validator: zjhvalidata, trigger: 'blur,change'}
          ],
          address: [
            {required: true, message: '请输入地址', trigger: 'change'}
          ],
          telephone: [
            {required: true, message: '请输入手机号', trigger: 'change'},
            {type: 'string', message: '请输入正确的手机号码', pattern: /^1[0-9]{10}$/, trigger: 'blur,change'}
          ]
        },
        bjblztmc: '', // 报件状态
        gridData: [
          {
            djlx: '抵押登记',
            qlr: '抵押权人信息',
            ywr: '抵押人信息 '
          },
          {
            djlx: '转移登记',
            qlr: '买方信息',
            ywr: '卖方信息 '
          },
          {
            djlx: '变更登记',
            qlr: '不动产权利人信息',
            ywr: '不动产权利人信息 '
          },
          {
            djlx: '转移登记（合并）',
            qlr: '抵押权人和买方信息',
            ywr: '卖方信息 '
          }
        ] // 提示信息弹框
      };
    },
    methods: {
      Look_reg() {  // 查看登记申请书
        this.regVisible = true;
        this.$store.commit('newPropose', this.proposer);
        this.$store.commit('newmortgage', this.mortgage);
        this.$store.commit('newbdcqk', this.bdcqk);
        this.$store.commit('newxwsx', this.xwsx);
      },
      _application_add(title, event) {    // 添加权利人与代理人
        if (this.bjblztmc === '待受理' || this.bjblztmc === '待接件' || this.bjblztmc === '已完成') {
          this.$message({
            message: this.bjblztmc + '中...',
            type: 'warning'
          });
          return false;
        }
        this.SqrqkForm.applyvalue = '';
        this.SqrqkForm.username = '';
        this.SqrqkForm.qlrlxvalue = '';
        this.SqrqkForm.zjlxvalue = '';
        this.SqrqkForm.zjh = '';
        this.SqrqkForm.address = '';
        this.SqrqkForm.telephone = '';
        this.getqlrzl();
        this.getqlrlx();
        this.handTitle = '添加' + title;
        this.Titlestate = title;
        switch (title) {
          case '权利人':
            this.Disablel = false;
            break;
          case '义务人':
            this.Disablel = true;
            this.SqrqkForm.applyvalue = '不动产权利人';
            this.Qlrzlcode = '1';
            break;
        }
        this.add_sqrqk = true;
      },
      _application_add_submit(SqrqkForm) {
        this.$refs[SqrqkForm].validate((valid) => {
          if (valid) {
            switch (this.Titlestate) {
              case '权利人':
                this.$http.post(this.$store.state.Host + '/BDCDJSQControl/saveQlr', {
                  jkzh: 200,
                  bjbh: this.bjbh,
                  qlrzl: this.Qlrzlcode,
                  qlrlx: this.Qlrlxcode,
                  qlrmc: this.SqrqkForm.username,
                  qlrzjzl: this.Qlrzjlxcode,
                  qlrzjh: this.SqrqkForm.zjh,
                  qlrdz: this.SqrqkForm.address,
                  qlrdh: this.SqrqkForm.telephone
                }).then((response) => {
                  response = response.body;
                  if (response.body === null) {
                    this.$notify({
                      title: '警告',
                      message: '保存失败，请生成报件编号。',
                      type: 'error'
                    });
                    this.add_apply = false;
                    return false;
                  }
                  switch (response.status) {
                    case '200':
                      this.$notify({
                        title: '提示',
                        message: '保存成功',
                        type: 'success'
                      });
                      if (this.proposer.qlrs[0].qlrmc === '' || this.proposer.qlrs[0].qlrmc === null) {
                        this.proposer.qlrs.splice(0, 1, response.body.body);
                        return false;
                      }
                      let index = this.proposer.qlrs.length;
                      this.proposer.qlrs.splice(index, 0, response.body.body);
                      break;
                    default:
                      this.$notify({
                        title: '警告',
                        message: '保存失败',
                        type: 'error'
                      });
                  }
                  this.add_sqrqk = false;
                });
                break;
              case '义务人':
                this.$http.post(this.$store.state.Host + '/BDCDJSQControl/saveObligor', {
                  jkzh: 200,
                  bjbh: this.bjbh,
                  ywrzl: this.Qlrzlcode,
                  ywrlx: this.Qlrlxcode,
                  ywrmc: this.SqrqkForm.username,
                  ywrzjzl: this.Ywrzjlxcode,
                  ywrzjh: this.SqrqkForm.zjh,
                  ywrdz: this.SqrqkForm.address,
                  ywrdh: this.SqrqkForm.telephone
                }).then((response) => {
                  response = response.body;
                  if (response.body === null) {
                    this.$notify({
                      title: '警告',
                      message: '保存失败。',
                      type: 'error'
                    });
                    return false;
                  }
                  switch (response.status) {
                    case '200':
                      this.$notify({
                        title: '提示',
                        message: '保存成功',
                        type: 'success'
                      });
                      if (this.proposer.ywrs[0].ywrmc === '' || this.proposer.ywrs[0].ywrmc === null) {
                        this.proposer.ywrs.splice(0, 1, response.body.body);
                        return false;
                      }
                      let index = this.proposer.ywrs.length;
                      this.proposer.ywrs.splice(index, 0, response.body.body);
                      break;
                    default:
                      this.$notify({
                        title: '警告',
                        message: '保存失败',
                        type: 'error'
                      });
                  }
                });
                break;
            }
            this.add_sqrqk = false;
            this.zjlxvalidator = '';
          } else {
            this.$notify.info({
              title: '提示',
              message: '请填写信息'
            });
            return false;
          }
        });
      },
      _edit_sqrqk(index, element, title) { // 修改权利人与义务人
//        console.log(element);
        if (this.bjblztmc === '待受理' || this.bjblztmc === '待接件' || this.bjblztmc === '已完成') {
          this.$message({
            message: this.bjblztmc + '中...',
            type: 'warning'
          });
          return false;
        }
        if (element === '' || element === null) {
          return false;
        }
        this.Editindex = index;
        this.Titlestate = title;
        let handata = element;
        switch (title) {
          case '权利人':
            this.Disablel = false;
            this.SqrqkForm.username = handata.qlrmc;
            this.SqrqkForm.zjh = handata.qlrzjh;
            this.SqrqkForm.address = handata.qlrdz;
            this.SqrqkForm.telephone = handata.qlrdh;
            this.SqrqkForm.applyvalue = handata.qlrzlmc;
            this.SqrqkForm.qlrlxvalue = handata.qlrlxmc;
            this.SqrqkForm.zjlxvalue = handata.qlrzjzlmc;
            this.qlrxh = handata.qlrunid;
            this.Qlrzlcode = handata.qlrzl;
            this.Qlrzjlxcode = handata.qlrzjzl;
            this.Qlrlxcode = handata.qlrlx;
            this.handTitle = '修改权利人';
            this.edit_sqrqk = true;
            break;
          case '权利人代理人':
            break;
          case '义务人':
            this.Disablel = true;
            this.SqrqkForm.username = handata.ywrmc;
            this.SqrqkForm.zjh = handata.ywrzjh;
            this.SqrqkForm.address = handata.ywrdz;
            this.SqrqkForm.telephone = handata.ywrdh;
            this.SqrqkForm.applyvalue = handata.ywrzlmc;
            this.SqrqkForm.qlrlxvalue = handata.ywrlxmc;
            this.SqrqkForm.zjlxvalue = handata.ywrzjzlmc;
            this.qlrxh = handata.ywrunid;
            this.Qlrzjlxcode = handata.ywrzjzl;
            this.Qlrlxcode = handata.ywrlx;
            this.SqrqkForm.applyvalue = '不动产权利人';
            this.Qlrzlcode = '1';
            this.handTitle = '修改义务人';
            this.edit_sqrqk = true;
            break;
          case '义务人代理人':
            break;
        }
        this.getqlrzl();
        this.getqlrlx();
      },
      _edit_sqrqk_submit(SqrqkForm) {
        let index = this.Editindex;
        this.$refs[SqrqkForm].validate((valid) => {
          if (valid) {
            switch (this.Titlestate) {
              case '权利人':
                this.$http.post(this.$store.state.Host + '/BDCDJSQControl/updateQlr', {
                  jkzh: 200,
                  bjbh: this.bjbh,
                  qlrunid: this.qlrxh,
                  qlrzl: this.Qlrzlcode,
                  qlrlx: this.Qlrlxcode,
                  qlrmc: this.SqrqkForm.username,
                  qlrzjzl: this.Qlrzjlxcode,
                  qlrzjh: this.SqrqkForm.zjh,
                  qlrdz: this.SqrqkForm.address,
                  qlrdh: this.SqrqkForm.telephone
                }).then((response) => {
                  response = response.body;
                  if (response.body.status === 400) {
                    this.$notify({
                      title: '警告',
                      message: '修改失败',
                      type: 'error'
                    });
                    this.edit_sqrqk = false;
                    return false;
                  }
                  if (response.status === '200') {
                    this.$notify({
                      title: '提示',
                      message: '修改成功',
                      type: 'success'
                    });
                    this.proposer.qlrs[index] = response.body.body;
                    this.edit_sqrqk = false;
                  } else {
                    this.$notify({
                      title: '警告',
                      message: '修改失败',
                      type: 'error'
                    });
                  }
                  this.edit_sqrqk = false;
                  this.zjlxvalidator = '';
                }, (error) => {
                  if (error.status === 401) {
                    this.$notify({
                      title: '警告',
                      message: error.body,
                      type: 'error'
                    });
                    this.$confirm('是否重新登录?', '提示', {
                      confirmButtonText: '确定',
                      cancelButtonText: '取消',
                      type: 'info'
                    }).then(() => {
                      this.$router.push({path: '/login'});
                    });
                  }
                });
                break;
              case '代理人':
                break;
              case '义务人':
                this.$http.post(this.$store.state.Host + '/BDCDJSQControl/updateOnligor', {
                  jkzh: 200,
                  bjbh: this.bjbh,
                  ywrunid: this.qlrxh,
                  ywrzl: this.Qlrzlcode,
                  ywrlx: this.Qlrlxcode,
                  ywrmc: this.SqrqkForm.username,
                  ywrzjzl: this.Qlrzjlxcode,
                  ywrzjh: this.SqrqkForm.zjh,
                  ywrdz: this.SqrqkForm.address,
                  ywrdh: this.SqrqkForm.telephone
                }).then((response) => {
                  response = response.body;
                  if (response.body.status === 400) {
                    this.$notify({
                      title: '警告',
                      message: '修改失败',
                      type: 'error'
                    });
                    this.edit_sqrqk = false;
                    return false;
                  }
                  if (response.status === '200') {
                    this.$notify({
                      title: '提示',
                      message: '修改成功',
                      type: 'success'
                    });
                    this.proposer.ywrs[index] = response.body.body;
                    this.edit_sqrqk = false;
                  } else {
                    this.$notify({
                      title: '警告',
                      message: '修改失败',
                      type: 'error'
                    });
                  }
                  this.edit_sqrqk = false;
                  this.zjlxvalidator = '';
                }, (error) => {
                  if (error.status === 401) {
                    this.$notify({
                      title: '警告',
                      message: error.body,
                      type: 'error'
                    });
                    this.$confirm('是否重新登录?', '提示', {
                      confirmButtonText: '确定',
                      cancelButtonText: '取消',
                      type: 'info'
                    }).then(() => {
                      this.$router.push({path: '/login'});
                    });
                  }
                });
                break;
            }
          } else {
            this.$notify.info({
              title: '提示',
              message: '请填写信息'
            });
            return false;
          }
        });
      },
      _delete_sqrqk(index, title) {
        if (this.bjblztmc === '待受理' || this.bjblztmc === '待接件' || this.bjblztmc === '已完成') {
          this.$message({
            message: this.bjblztmc + '中...',
            type: 'warning'
          });
          return false;
        }
        switch (title) {
          case '权利人':
            let xh = this.proposer.qlrs[index].qlrunid;
            this.$http.post(this.$store.state.Host + '/BDCDJSQControl/deleteQlr', {
              jkzh: '200',
              bjbh: this.bjbh,
              qlrunid: xh
            }).then((response) => {
              response = response.body.body;
              switch (response.status) {
                case '200':
                  this.$notify({
                    title: '提示',
                    message: '删除成功',
                    type: 'success'
                  });
                  if (this.proposer.qlrs.length === 1 || this.proposer.qlrs.length === '1') {
                    this.proposer.qlrs[0].qlrmc = '';
                    this.proposer.qlrs[0].qlrzjzlmc = '';
                    this.proposer.qlrs[0].qlrzjh = '';
                    this.proposer.qlrs[0].qlrdz = '';
                    this.proposer.qlrs[0].qlrdh = '';
                    return false;
                  }
                  this.proposer.qlrs.splice(index, 1);
                  break;
                default:
                  this.$notify({
                    title: '警告',
                    message: '删除失败',
                    type: 'error'
                  });
              }
            }, (error) => {
              if (error.status === 401) {
                this.$notify({
                  title: '警告',
                  message: error.body,
                  type: 'error'
                });
                this.$confirm('是否重新登录?', '提示', {
                  confirmButtonText: '确定',
                  cancelButtonText: '取消',
                  type: 'info'
                }).then(() => {
                  this.$router.push({path: '/login'});
                });
              }
            });
            break;
          case '义务人':
            let ywrxh = this.proposer.ywrs[index].ywrunid;
            this.$http.post(this.$store.state.Host + '/BDCDJSQControl/deleteonlior', {
              jkzh: '200',
              bjbh: this.bjbh,
              ywrunid: ywrxh
            }).then((response) => {
              response = response.body;
              switch (response.status) {
                case '200':
                  this.$notify({
                    title: '提示',
                    message: '删除成功',
                    type: 'success'
                  });
                  if (this.proposer.ywrs.length === 1 || this.proposer.ywrs.length === '1') {
                    this.proposer.ywrs[0].ywrmc = '';
                    this.proposer.ywrs[0].ywrzjzlmc = '';
                    this.proposer.ywrs[0].ywrzjh = '';
                    this.proposer.ywrs[0].ywrdz = '';
                    this.proposer.ywrs[0].ywrdh = '';
                    return false;
                  }
                  this.proposer.ywrs.splice(index, 1);
                  break;
                default:
                  this.$notify({
                    title: '警告',
                    message: '删除失败',
                    type: 'error'
                  });
              }
            }, (error) => {
              if (error.status === 401) {
                this.$notify({
                  title: '警告',
                  message: error.body,
                  type: 'error'
                });
                this.$confirm('是否重新登录?', '提示', {
                  confirmButtonText: '确定',
                  cancelButtonText: '取消',
                  type: 'info'
                }).then(() => {
                  this.$router.push({path: '/login'});
                });
              }
            });
            break;
        }
      },
      _add_dlr(title) {  // 添加权利人代理人与义务人代理人
        if (this.bjblztmc === '待受理' || this.bjblztmc === '待接件' || this.bjblztmc === '已完成') {
          this.$message({
            message: this.bjblztmc + '中...',
            type: 'warning'
          });
          return false;
        }
        this.AddForm.username = '';
        this.AddForm.zjh = '';
        this.AddForm.address = '';
        this.AddForm.telephone = '';
        this.AddForm.zjlxvalue = '';
        this.Titlestate = title;
        this.handTitle = '添加' + title;
        this.add_dlr = true;
        this.getzjlx();
      },
      _add_qlr_submit(AddForm) {
        this.$refs[AddForm].validate((valid) => {
          if (valid) {
            switch (this.Titlestate) {
              case '权利人代理人':
                this.proposer.qlrdlr.qlrdlrmc = this.AddForm.username;
                this.proposer.qlrdlr.qlrdlrzjh = this.AddForm.zjh;
                this.proposer.qlrdlr.qlrdlrdz = this.AddForm.address;
                this.proposer.qlrdlr.qlrdlrdh = this.AddForm.telephone;
                this.proposer.qlrdlr.qlrdlrzjzlmc = this.AddForm.zjlxvalue;
                break;
              case '义务人代理人':
                this.proposer.ywrdlr.ywrdlrmc = this.AddForm.username;
                this.proposer.ywrdlr.ywrdlrzjh = this.AddForm.zjh;
                this.proposer.ywrdlr.ywrdlrdz = this.AddForm.address;
                this.proposer.ywrdlr.ywrdlrdh = this.AddForm.telephone;
                this.proposer.ywrdlr.ywrdlrzjzlmc = this.AddForm.zjlxvalue;
                break;
            }
            this.add_dlr = false;
            this.zjlxvalidator = '';
          }
        });
      },
      _edit_sqrqkdlr(data, title) {  // 修改权利人代理人与义务人代理人
        if (this.bjblztmc === '待受理' || this.bjblztmc === '待接件' || this.bjblztmc === '已完成') {
          this.$message({
            message: this.bjblztmc + '中...',
            type: 'warning'
          });
          return false;
        }
        if (data === '') {
          return false;
        }
        this.Titlestate = title;
        this.handTitle = '修改' + title;
        this.getzjlx();
        switch (title) {
          case '权利人代理人':
            this.AddForm.username = data.qlrdlrmc;
            this.AddForm.zjh = data.qlrdlrzjh;
            this.AddForm.address = data.qlrdlrdz;
            this.AddForm.telephone = data.qlrdlrdh;
            this.AddForm.zjlxvalue = data.qlrdlrzjzlmc;
            break;
          case '义务人代理人':
            this.AddForm.username = data.ywrdlrmc;
            this.AddForm.zjh = data.ywrdlrzjh;
            this.AddForm.address = data.ywrdlrdz;
            this.AddForm.telephone = data.ywrdlrdh;
            this.AddForm.zjlxvalue = data.ywrdlrzjzlmc;
            break;
        }
        this.add_dlr = true;
      },
      getqlrzl() {    // 获取权利人种类
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: 10007
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                code: data[i].code,
                value: data[i].name
              };
              arr.push(json);
            }
            this.Qlrzl = arr;
          }
        });
      },
      _clerar_sqrqkdlr(data, title) {
        if (this.bjblztmc === '待受理') {
          this.$message({
            message: '报件处于待受理中，不能删除。',
            type: 'warning'
          });
          return false;
        }
        switch (title) {
          case '权利人代理人':
            this.proposer.qlrdlr.qlrdlrmc = '';
            this.proposer.qlrdlr.qlrdlrzjh = '';
            this.proposer.qlrdlr.qlrdlrdz = '';
            this.proposer.qlrdlr.qlrdlrdh = '';
            this.proposer.qlrdlr.qlrdlrzjzl = '';
            this.proposer.qlrdlr.qlrdlrzjzlmc = '';
            break;
          case '义务人代理人':
            this.proposer.ywrdlr.ywrdlrmc = '';
            this.proposer.ywrdlr.ywrdlrzjh = '';
            this.proposer.ywrdlr.ywrdlrdz = '';
            this.proposer.ywrdlr.ywrdlrdh = '';
            this.proposer.ywrdlr.ywrdlrzjzl = '';
            this.proposer.ywrdlr.ywrdlrzjzlmc = '';
            break;
        }
      },
      _qlrzlchange(val) {
        let options = this.Qlrzl;
        for (var i = 0; i < options.length; i++) {
          if (val.indexOf(options[i].value) > -1) {
            this.Qlrzlcode = options[i].code;
          }
        }
      },
      getqlrlx() {    // 获取权利人类型
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: 10005
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                code: data[i].code,
                value: data[i].name
              };
              arr.push(json);
            }
            this.Qlrlx = arr;
          }
        });
      },
      _qlrlxchange(val) {
        let options = this.Qlrlx;
        for (var i = 0; i < options.length; i++) {
          if (options[i].code === '' || options[i].code === null) {
            return false;
          }
          if (val.indexOf(options[i].value) > -1) {
            this.Qlrlxcode = options[i].code;
          }
        }
      },
      getzjlx() {    // 获取证件种类
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: 130
        }).then((response) => {
          response = response.body;
          if (response.body === null || response.body === '') {
            return false;
          }
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                code: data[i].code,
                value: data[i].name
              };
              arr.push(json);
            }
            this.zjlx = arr;
          }
        }, (error) => {
          if (error.status === 401) {
            this.$notify({
              title: '警告',
              message: error.body,
              type: 'error'
            });
            this.$confirm('是否重新登录?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'info'
            }).then(() => {
              this.$router.push({path: '/login'});
            });
          }
        });
      },
      _zjlxchange(val) {
        if (val === '' || val === null || val === undefined) {
          return false;
        }
        this.zjlxvalidator = val;
        let options = this.zjlx;
        for (var i = 0; i < options.length; i++) {
          if (options[i].code === '' || options[i].code === null) {
            return false;
          }
          if (val.indexOf(options[i].value) > -1) {
            switch (this.Titlestate) {
              case '权利人':
                this.Qlrzjlxcode = options[i].code;
                break;
              case '义务人':
                this.Ywrzjlxcode = options[i].code;
                break;
              case '权利人代理人':
                this.qlrdlrzjlxcode = options[i].code;
                break;
              case '义务人代理人':
                this.ywrdlrzjlxcode = options[i].code;
                break;
            }
          }
        }
      },
      getdyfs() {    // 获取抵押方式
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: 10006
        }).then((response) => {
          response = response.body;
          if (response.body === null || response.body === '') {
            return false;
          }
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                value: data[i].name,
                code: data[i].code
              };
              arr.push(json);
            }
            this.dyfsoptions = arr;
          }
        });
      },
      _dyfschange(val) {
        if (val === null || val === '') {
          return false;
        }
        let options = this.dyfsoptions;
        for (var i = 0; i < options.length; i++) {
          if (options[i].code === '' || options[i].code === null) {
            return false;
          }
          if (val.indexOf(options[i].value) > -1) {
            this.dyfscode = options[i].code;
          }
        }
      },
      gettdyt() {    // 获取土地用途下拉列表
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: 10008
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                value: data[i].name,
                code: data[i].code
              };
              arr.push(json);
            }
            this.tdytoptions = arr;
          }
        });
      },
      _tdytchange(val) {
        if (val === null || val === '') {
          return false;
        }
        let options = this.tdytoptions;
        for (var i = 0; i < options.length; i++) {
          if (options[i].code === '' || options[i].code === null) {
            return false;
          }
          if (val.indexOf(options[i].value) > -1) {
            this.tdytcode = options[i].code;
          }
        }
      },
      getfwyt() {    // 获取房屋用途下拉列表
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: 117
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                value: data[i].name,
                code: data[i].code
              };
              arr.push(json);
            }
            this.fwytoptions = arr;
          }
        });
      },
      _fwytchange(val) {
        if (val === null || val === '') {
          return false;
        }
        let options = this.fwytoptions;
        for (var i = 0; i < options.length; i++) {
          if (options[i].code === '' || options[i].code === null) {
            return false;
          }
          if (val.indexOf(options[i].value) > -1) {
            this.fwytcode = options[i].code;
          }
        }
      },
      getqlxz() {    // 获取权利性质下拉列表
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: 109
        }).then((response) => {
          response = response.body;
          if (response.body === null || response.body === '') {
            return false;
          }
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                value: data[i].name,
                code: data[i].code
              };
              arr.push(json);
            }
            this.qlxzoptions = arr;
          }
        });
      },
      _qlxzchange(val) {
        if (val === null || val === '') {
          return false;
        }
        let options = this.qlxzoptions;
        for (var i = 0; i < options.length; i++) {
          if (options[i].code === '' || options[i].code === null) {
            return false;
          }
          if (val.indexOf(options[i].value) > -1) {
            this.qlxzcode = options[i].code;
          }
        }
      },
      getssqx() {    // 获取所属区县下拉列表
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: '10002',
          pcode: ''
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                value: data[i].name,
                code: data[i].code
              };
              arr.push(json);
            }
            this.ssqxoptions = arr;
            this.ssqxoptions1 = arr;
          }
        });
      },
      _ssqxchange(val) {
        if (val === null || val === '' || val === this.ssqxvalue) {
          return false;
        }
        let options = this.ssqxoptions1;
        for (var i = 0; i < options.length; i++) {
          if (options[i].code === '' || options[i].code === null) {
            return false;
          }
          if (val.indexOf(options[i].value) > -1) {
            this.selectcode.ssqx = options[i].code;
            this._typeoption_submit1();
          }
        }
        this.ssqxvalue = '';
      },
      _Quxchange(val) {
        this.ssqxvalue = val;
        if (val === null || val === '') {
          return false;
        }
        let options = this.ssqxoptions;
        for (var i = 0; i < options.length; i++) {
          if (options[i].code === '' || options[i].code === null) {
            return false;
          }
          if (val.indexOf(options[i].value) > -1) {
            this.selectcode.ssqx = options[i].code;
          }
        }
      },
      getdjlx() {    // 获取登记类型下拉列表
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: '121',
          pcode: ''
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              if (data[i].name === '抵押登记') {
                let json = {
                  value: data[i].name,
                  code: data[i].code
                };
                arr.push(json);
              }
            }
            this.djlxoptions = arr;
          }
        });
      },
      _djlxchange(val) {
        if (val === null || val === '') {
          return false;
        }
        let options = this.djlxoptions;
        for (var i = 0; i < options.length; i++) {
          if (options[i].code === '' || options[i].code === null) {
            return false;
          }
          if (val.indexOf(options[i].value) > -1) {
            this.selectcode.djlx = options[i].code;
            this.getdjzlx(options[i].code);
          }
        }
      },
      getdjzlx(code) {    // 获取所登记类型下拉列表
        if (code === null || code === '') {
          return false;
        }
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: '10000',
          pcode: code
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                value: data[i].name,
                code: data[i].code
              };
              arr.push(json);
            }
            this.djzlxoptions = arr;
          }
        });
      },
      _djzlxchange(val) {
        if (val === null || val === '' || val === this.djzlxvalue) {
          return false;
        }
        let options = this.djzlxoptions;
        for (var i = 0; i < options.length; i++) {
          if (options[i].code === '' || options[i].code === null) {
            return false;
          }
          if (val.indexOf(options[i].value) > -1) {
            this.selectcode.djzlx = options[i].code;
            this._typeoption_submit1();
          }
        }
        this.djzlxvalue = '';
      },
      typeoption_1(code) {  // 获取一级表单数据
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: code
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              if (data[i].name === '抵押登记') {
                let json = {
                  code: data[i].code,
                  subcode: data[i].subclass,
                  label: data[i].name,
                  cities: []
                };
                arr.push(json);
              }
            }
            this.Typeoption = arr;
          }
        });
      },
      typeoption_2(index, code) {  // 获取二级表单数据
        if (code === '' || code === null) {
          return false;
        }
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: '10000',
          pcode: code
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                code: data[i].code,
                label: data[i].name
              };
              arr.push(json);
            }
            this.Typeoption[index].cities = arr;
          }
        });
      },
      handleItemChange(val) {  // 级联选择
        let options = this.Typeoption;
        setTimeout(_ => {
          for (var i = 0; i < options.length; i++) {
            if (val.indexOf(options[i].label) > -1 && !options[i].cities.length) {
              this.typeoption_2(i, options[i].code);
//              this.getdjzlx(options[i].code);
            }
          }
        }, 300);
      },
      _checked(val) {  // 保存选项编码
        let options = this.Typeoption;
        for (var i = 0; i < options.length; i++) {
          if (val.indexOf(options[i].label) > -1) {
            this.selectcode.djlx = options[i].code;
            for (var k = 0; k < options[i].cities.length; k++) {
              if (val.indexOf(options[i].cities[k].label) > -1) {
                this.selectcode.djzlx = options[i].cities[k].code;
                this.djzlxvalue = options[i].cities[k].label;
              }
            }
          }
        }
      },
      _getDate(style) {
        let date = new Date();
        return formatDate(date, style);
      },
      _typeoption_submit() {  // 申请报件
        let username = localStorage.getItem('username');
        let jyrq = this._getDate('yyyy-MM-dd');
        let jysj = this._getDate('hh:mm:ss');
        this.$http.post(this.$store.state.Host + '/BDCDJSQControl/applyBJBH', {
          jkzh: 200,
          bjssqx: this.selectcode.ssqx,
          djlx: this.selectcode.djlx,
          djsjlx: this.selectcode.djzlx,
          jyczymc: username,
          jyczyzh: username,
          jyqd: 'PC',
          jyrq: jyrq,
          jysj: jysj
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            this.application = response.body;
            this.djlx = this.application.body.sqdjsy;
            this.bjbh = this.application.body.bjbh;
            this.$store.commit('newBjbh', this.application.body.bjbh);
            if (this.application.body.sqrqk.qlrs.length !== 0) {
              this.proposer.qlrs = this.application.body.sqrqk.qlrs;
            }
            if (this.application.body.sqrqk.qlrdlr.length !== 0) {
              this.proposer.qlrdlr = this.application.body.sqrqk.qlrdlr;
            }
            if (this.application.body.sqrqk.ywrdlr.length !== 0) {
              this.proposer.ywrdlr = this.application.body.sqrqk.ywrdlr;
            }
            if (this.application.body.sqrqk.ywrs.length !== 0) {
              this.proposer.ywrs = this.application.body.sqrqk.ywrs;
            }
            this.mortgage = this.application.body.dyqk;
            this.bdcqk = this.application.body.bdcqk;
            this.xwsx = this.application.body.xwsx;
            this.upDatalist = this.application.body.qyclmls;
            this.bdcqk.ssqxmc = this.ssqxvalue;
          } else {
            this.$message.error('暂无数据');
          }
          this.bjblztmc = '';
          this.typeVisible = false;
          this.checkeVsibke = false;
        }, (error) => {
          if (error.status === 401) {
            this.$notify({
              title: '警告',
              message: error.body,
              type: 'error'
            });
            this.$confirm('是否重新登录?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'info'
            }).then(() => {
              this.$router.push({path: '/login'});
            });
          }
        });
      },
      _typeoption_submit1() {  // 申请报件
        if (this.selectcode.ssqx === null || this.selectcode.ssqx === '' ||
          this.selectcode.djzlx === null || this.selectcode.djzlx === '') {
          return false;
        }
        let username = localStorage.getItem('username');
        let jyrq = this._getDate('yyyy-MM-dd');
        let jysj = this._getDate('hh:mm:ss');
        this.$http.post(this.$store.state.Host + '/BDCDJSQControl/applyBJBH', {
          jkzh: 200,
          bjssqx: this.selectcode.ssqx,
          djlx: this.selectcode.djlx,
          djsjlx: this.selectcode.djzlx,
          jyczymc: username,
          jyczyzh: username,
          jyqd: 'PC',
          jyrq: jyrq,
          jysj: jysj
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            this.application = response.body;
            this.djlx = this.application.body.sqdjsy;
            this.bjbh = this.application.body.bjbh;
            this.$store.commit('newBjbh', this.application.body.bjbh);
            let clearqlr = [
              {
                bjbh: '',
                qlrunid: '',
                qlrxh: '',
                qlrzl: '',
                qlrzlmc: '',
                qlrlx: '',
                qlrlxmc: '',
                qlrmc: '',
                qlrzjzl: '',
                qlrzjzlmc: '',
                qlrzjh: '',
                qlrdh: '',
                qlrdz: ''
              }
            ];
            let clearyws = [
              {
                bjbh: '',
                ywrdh: '',
                ywrdz: '',
                ywrlx: '',
                ywrlxmc: '',
                ywrmc: '',
                ywrunid: '',
                ywrxh: '',
                ywrzjh: '',
                ywrzjzl: '',
                ywrzjzlmc: '',
                ywrzl: '',
                ywrzlmc: ''
              }
            ];
            this.proposer.qlrs = clearqlr;
            this.proposer.ywrs = clearyws;
            if (this.application.body.sqrqk.qlrs.length !== 0) {
              this.proposer.qlrs = this.application.body.sqrqk.qlrs;
            }
            if (this.application.body.sqrqk.ywrs.length !== 0) {
              this.proposer.ywrs = this.application.body.sqrqk.ywrs;
            }
            this.upDatalist = this.application.body.qyclmls;
          } else {
            this.$message.error('暂无数据');
          }
          this.bjblztmc = '';
          this.typeVisible = false;
          this.checkeVsibke = false;
        }, (error) => {
          if (error.status === 401) {
            this.$notify({
              title: '警告',
              message: error.body,
              type: 'error'
            });
            this.$confirm('是否重新登录?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'info'
            }).then(() => {
              this.$router.push({path: '/login'});
            });
          }
        });
      },
      _Keephand() { // 保存权利人信息
        this.handVisible = false;
      },
      _apply_submit() {  // 申请提交
        if (this.bjblztmc === '待受理' || this.bjblztmc === '待接件' || this.bjblztmc === '已完成') {
          this.$message({
            message: this.bjblztmc + '中...',
            type: 'warning'
          });
          return false;
        }
        if (this.fwytcode === '' || this.fwytcode === null) {
          this.$message({
            message: '请选择房屋用途',
            type: 'warning'
          });
          return false;
        }
        if (this.tdytcode === '' || this.tdytcode === null) {
          this.$message({
            message: '请选择土地用途',
            type: 'warning'
          });
          return false;
        }
        if (this.qlxzcode === '' || this.qlxzcode === null) {
          this.$message({
            message: '请选择权利性质',
            type: 'warning'
          });
          return false;
        }
        if (this.dyfscode === '' || this.dyfscode === null) {
          this.$message({
            message: '请选择抵押方式',
            type: 'warning'
          });
          return false;
        }
        if (this.selectcode.ssqx === '') {
          this.$message({
            message: '请选择所属区县',
            type: 'warning'
          });
          return false;
        }
        if (this.bdcqk.bdcdyh === null || this.bdcqk.bdcdyh === '') {
          this.bdcqk.bdcdyh = '0';
        }
        this.$confirm('此操作提交之后将无法修改, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'info'
        }).then(() => {
          this.$http.post(this.$store.state.Host + '/BDCDJSQControl/submitDJSQ', {
            jkzh: 200,
            bjbh: this.bjbh,
            qlrdlrmc: this.proposer.qlrdlr.qlrdlrmc,
            qlrdlrzjzl: this.qlrdlrzjlxcode,
            qlrdlrzjh: this.proposer.qlrdlr.qlrdlrzjh,
            qlrdlrdh: this.proposer.qlrdlr.qlrdlrdh,
            qlrdlrdz: this.proposer.qlrdlr.qlrdlrdz,
            ywrdlrmc: this.proposer.ywrdlr.ywrdlrmc,
            ywrdlrzjzl: this.ywrdlrzjlxcode,
            ywrdlrzjh: this.proposer.ywrdlr.ywrdlrzjh,
            ywrdlrdh: this.proposer.ywrdlr.ywrdlrdh,
            ywrdlrdz: this.proposer.ywrdlr.ywrdlrdz,
            bdcdyh: this.bdcqk.bdcdyh,
            bdcqzhy: this.bdcqk.bdcqzshy,
            bdcqzhx: this.bdcqk.bdcqzshx,
            zl: this.bdcqk.zl,
            ssqx: this.selectcode.ssqx,  // 所属区县
            fwyt: this.fwytcode,
            fwmj: this.bdcqk.fwmj,
            tdyt: this.tdytcode,
            tdmj: this.bdcqk.tdmj,
            tdsyqssj: this.bdcqk.tdsyqssj,
            tdsyjssj: this.bdcqk.tdsyjssj,
            qlxz: this.qlxzcode,
            dyfs: this.dyfscode,
            bdbzqse: this.mortgage.bdbzqse,
            dyhtqdrq: this.mortgage.dyhtqdrq,
            zwlxqssj: this.mortgage.zwlxqssj,
            zwlxjssj: this.mortgage.zwlxjssj,
            bdcpgjg: this.mortgage.bdcpgjg,
            tdpgjg: this.mortgage.tdpgjg,
            fcpgjg: this.mortgage.fcpgjg,
            dbfw: this.mortgage.dbfw,
            zjjzwdyfw: this.mortgage.zjjzwdyfw,
            fcdymj: this.mortgage.fcdymj,
            tddymj: this.mortgage.tddymj,
            dkywbh: this.mortgage.dkywbh,
            dkzh: this.mortgage.dkzh,
            yhdyywh: this.mortgage.yhdyywh,
            zqr: this.mortgage.zqr,
            zwr: this.mortgage.zwr,
            sfgy: '',  // 是否共有
            gyfs: '',
            afgyfeqk: '',
            sffbcz: '',
            qtsm: ''
          }).then((response) => {
            response = response.body;
            if (response.body === null || response.body === '') {
              this.$message({
                message: '提交失败',
                type: 'error'
              });
              return false;
            }
            if (response.body.body === null || response.body.body === '') {
              this.$message({
                message: response.body.message,
                type: 'error'
              });
              return false;
            }
            switch (response.status) {
              case '200':
                this.$http.post(this.$store.state.Host + '/BDCDJSQControl/SaveDJSQ', {
                  jkzh: 200,
                  bjbh: this.bjbh
                }).then((response) => {
                  response = response.body;
                  if (response.body.body === null) {
                    this.$message({
                      message: response.body.message,
                      type: 'error'
                    });
                    return false;
                  }
                  if (response.status === '200') {
                    this.$message({
                      message: '提交成功。',
                      type: 'success'
                    });
                  } else {
                    this.$message({
                      message: '提交失败',
                      type: 'error'
                    });
                  }
                });
                break;
              case '40002001':
                this.$message({
                  message: '信息提交不完整,先完善权利人、义务人、权源材料信息。',
                  type: 'error'
                });
                break;
              default :
                this.$message({
                  message: '提交失败!',
                  type: 'error'
                });
            }
          }, (error) => {
            if (error.status === 401) {
              this.$notify({
                title: '警告',
                message: error.body,
                type: 'error'
              });
              this.$confirm('是否重新登录?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'info'
              }).then(() => {
                this.$router.push({path: '/login'});
              });
            }
          });
        });
      },
      handleClose(done) {  // 弹框
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
            this.regVisible = false;
            this.handVisible = false;
          })
          .catch(_ => {
          });
      },
      Close() {
        this.handVisible = false;
        this.regVisible = false;
      },
      _zwlxqstime(date) {
        this.mortgage.zwlxqssj = date;
      },
      _zwlxjstime(date) {
        this.mortgage.zwlxjssj = date;
      },
      _dyhtqdrqtime(date) {
        this.mortgage.dyhtqdrq = date;
      },
      _tdsyqssjtime(date) {
        this.bdcqk.tdsyqssj = date;
      },
      _tdsyjssjtime(date) {
        this.bdcqk.tdsyjssj = date;
      },
      _print() {
        this.$refs.register._print();
      },
      _printapply() {
        this.$store.commit('newPropose', this.proposer);
        this.$store.commit('newmortgage', this.mortgage);
        this.$store.commit('newbdcqk', this.bdcqk);
        this.$store.commit('newxwsx', this.xwsx);
        this.$router.push({path: '/print'});
        this.$nextTick(() => {
          window.print();
          this.$router.push({path: '/index'});
        });
      },
      _newcreateapply() {
        let clearproposer = {
          qlrs: [
            {
              bjbh: '',
              qlrunid: '',
              qlrxh: '',
              qlrzl: '',
              qlrzlmc: '',
              qlrlx: '',
              qlrlxmc: '',
              qlrmc: '',
              qlrzjzl: '',
              qlrzjzlmc: '',
              qlrzjh: '',
              qlrdh: '',
              qlrdz: ''
            }
          ],
          ywrs: [
            {
              bjbh: '',
              ywrdh: '',
              ywrdz: '',
              ywrlx: '',
              ywrlxmc: '',
              ywrmc: '',
              ywrunid: '',
              ywrxh: '',
              ywrzjh: '',
              ywrzjzl: '',
              ywrzjzlmc: '',
              ywrzl: '',
              ywrzlmc: ''
            }
          ],
          qlrdlr: {
            qlrdlrdh: '',
            qlrdlrdz: '',
            qlrdlrmc: '',
            qlrdlrzjh: '',
            qlrdlrzjzl: '',
            qlrdlrzjzlmc: ''
          },
          ywrdlr: {
            ywrdlrdh: '',
            ywrdlrdz: '',
            ywrdlrmc: '',
            ywrdlrzjh: '',
            ywrdlrzjzl: '',
            ywrdlrzjzlmc: ''
          }
        };
        let cleardjlx = {
          djlx: '',
          djlxmc: '',
          djzlx: '',
          djzlxmc: ''
        };
        let clearmortgage = {  // 抵押情况
          dyfs: '',
          dyfsmc: '',
          bdcpgjg: '',
          dbfw: '',
          dkywbh: '',
          dkzh: '',
          dyhtqdrq: '',
          fcdymj: '',
          fcpgjg: '',
          tddymj: '',
          tdpgjg: '',
          yhdyywh: '',
          bdbzqse: '',
          zjjzwdyfw: '',
          zqr: '',
          zwlxjssj: '',
          zwlxqssj: '',
          zwr: ''
        };
        let clearbdcqk = {    //  不动产情况
          bdcqzshy: '',
          bdcqzshx: '',
          bdcdyh: '',
          zl: '',
          ssqx: '',
          ssqxmc: '',
          fwyt: '',
          fwytmc: '',
          fwmj: '',
          tdyt: '',
          tdytmc: '',
          tdmj: '',
          tdsyqssj: '',
          tdsyjssj: '',
          qlxz: '',
          qlxzmc: ''
        };
        let clearupdatalist = [
          {
            bjbh: '',
            mlxh: '',
            mlmc: '',
            mlwjlx: '',
            mlwjsl: '',
            state: ''
          }
        ];
        this.proposer = clearproposer;
        this.djlx = cleardjlx;
        this.bjbh = '';
        this.mortgage = clearmortgage;
        this.bdcqk = clearbdcqk;
        this.upDatalist = clearupdatalist;
        this.SqrqkForm.applyvalue = '';
        this.SqrqkForm.username = '';
        this.SqrqkForm.qlrlxvalue = '';
        this.SqrqkForm.zjlxvalue = '';
        this.SqrqkForm.zjh = '';
        this.SqrqkForm.address = '';
        this.SqrqkForm.telephone = '';
        this.$store.commit('newBjbh', '');
        this.$store.commit('application', '');
        this.bjblztmc = '';
        this.ssqxvalue = '';
        this.selectdjlx = [];
        this.selectcode.ssqx = '';
        this.fwytcode = '';
        this.tdytcode = '';
        this.qlxzcode = '';
        this.dyfscode = '';
        setTimeout(() => {
          this.typeVisible = true;
        }, 1000);
      }
    },
    created() {
      this.$http.post(this.$store.state.Host + '/TokrnControl/getzdfl', {}).then((response) => {
        response = response.body;
        if (response.body === null || response.body === '') {
          return false;
        }
        if (response.status === '200') {
          this.code = response.body[1].code;
          this.typename = '请选择' + response.body[1].name;
          this.typeoption_1(this.code);
        }
      });
      this.getzjlx();
      this.getdyfs();
      this.getfwyt();
      this.gettdyt();
      this.getqlxz();
      this.getssqx();
      this.getdjlx();
    },
    mounted() {
    },
    activated() {
      this.checkeVsibke = false;
      if (this.$store.state.application !== '') {
        this.$emit('acIndex', '/index/application');
//        console.log(this.$store.state.application);
        let Lookdata = this.$store.state.application;
        this.djlx = Lookdata.sqdjsy;
        this.bjbh = Lookdata.bjbh;
        if (Lookdata.sqrqk.qlrs.length !== 0) {
          this.proposer.qlrs = Lookdata.sqrqk.qlrs;
        }
        if (Lookdata.sqrqk.qlrdlr.length !== 0) {
          this.proposer.qlrdlr = Lookdata.sqrqk.qlrdlr;
        }
        if (Lookdata.sqrqk.ywrdlr.length !== 0) {
          this.proposer.ywrdlr = Lookdata.sqrqk.ywrdlr;
        }
        if (Lookdata.sqrqk.ywrs.length !== 0) {
          this.proposer.ywrs = Lookdata.sqrqk.ywrs;
        }
        this.mortgage = Lookdata.dyqk;
        this.bdcqk = Lookdata.bdcqk;
        this.xwsx = Lookdata.xwsx;
        this.upDatalist = Lookdata.qyclmls;
        this.fwytcode = Lookdata.bdcqk.fwyt;
        this.tdytcode = Lookdata.bdcqk.tdyt;
        this.qlxzcode = Lookdata.bdcqk.qlxz;
        this.bdcqk.ssqxmc = Lookdata.bjssqxmc;
        this.selectcode.ssqx = Lookdata.bjssqx;
        this.selectcode.djlx = Lookdata.sqdjsy.djlx;
        this.selectcode.djzlx = Lookdata.sqdjsy.djzlx;
        this.qlrzjlxcode = Lookdata.sqrqk.qlrdlr.qlrdlrzjzl;
        this.ywrzjlxcode = Lookdata.sqrqk.ywrdlr.ywrdlrzjzl;
        this.djzlxvalue = Lookdata.sqdjsy.djzlxmc;
        this.ssqxvalue = Lookdata.bdcqk.ssqxmc;
        this.bjblztmc = Lookdata.bjblztmc;
        this.$store.commit('newBjbh', Lookdata.bjbh);
      } else {
        let clearproposer = {
          qlrs: [
            {
              bjbh: '',
              qlrunid: '',
              qlrxh: '',
              qlrzl: '',
              qlrzlmc: '',
              qlrlx: '',
              qlrlxmc: '',
              qlrmc: '',
              qlrzjzl: '',
              qlrzjzlmc: '',
              qlrzjh: '',
              qlrdh: '',
              qlrdz: ''
            }
          ],
          ywrs: [
            {
              bjbh: '',
              ywrdh: '',
              ywrdz: '',
              ywrlx: '',
              ywrlxmc: '',
              ywrmc: '',
              ywrunid: '',
              ywrxh: '',
              ywrzjh: '',
              ywrzjzl: '',
              ywrzjzlmc: '',
              ywrzl: '',
              ywrzlmc: ''
            }
          ],
          qlrdlr: {
            qlrdlrdh: '',
            qlrdlrdz: '',
            qlrdlrmc: '',
            qlrdlrzjh: '',
            qlrdlrzjzl: '',
            qlrdlrzjzlmc: ''
          },
          ywrdlr: {
            ywrdlrdh: '',
            ywrdlrdz: '',
            ywrdlrmc: '',
            ywrdlrzjh: '',
            ywrdlrzjzl: '',
            ywrdlrzjzlmc: ''
          }
        };
        let cleardjlx = {
          djlx: '',
          djlxmc: '',
          djzlx: '',
          djzlxmc: ''
        };
        let clearmortgage = {  // 抵押情况
          dyfs: '',
          dyfsmc: '',
          bdcpgjg: '',
          dbfw: '',
          dkywbh: '',
          dkzh: '',
          dyhtqdrq: '',
          fcdymj: '',
          fcpgjg: '',
          tddymj: '',
          tdpgjg: '',
          yhdyywh: '',
          bdbzqse: '',
          zjjzwdyfw: '',
          zqr: '',
          zwlxjssj: '',
          zwlxqssj: '',
          zwr: ''
        };
        let clearbdcqk = {    //  不动产情况
          bdcqzshy: '',
          bdcqzshx: '',
          bdcdyh: '',
          zl: '',
          ssqx: '',
          ssqxmc: '',
          fwyt: '',
          fwytmc: '',
          fwmj: '',
          tdyt: '',
          tdytmc: '',
          tdmj: '',
          tdsyqssj: '',
          tdsyjssj: '',
          qlxz: '',
          qlxzmc: ''
        };
        let clearupdatalist = [
          {
            bjbh: '',
            mlxh: '',
            mlmc: '',
            mlwjlx: '',
            mlwjsl: '',
            state: ''
          }
        ];
        this.proposer = clearproposer;
        this.djlx = cleardjlx;
        this.mortgage = clearmortgage;
        this.bdcqk = clearbdcqk;
        this.upDatalist = clearupdatalist;
        this.SqrqkForm.applyvalue = '';
        this.SqrqkForm.username = '';
        this.SqrqkForm.qlrlxvalue = '';
        this.SqrqkForm.zjlxvalue = '';
        this.SqrqkForm.zjh = '';
        this.SqrqkForm.address = '';
        this.SqrqkForm.telephone = '';
        this.$store.commit('newBjbh', '');
        this.$store.commit('application', '');
        this.bjblztmc = '';
        this.ssqxvalue = '';
        this.selectdjlx = [];
        this.selectcode.ssqx = '';
        this.fwytcode = '';
        this.tdytcode = '';
        this.qlxzcode = '';
        this.dyfscode = '';
      }
      if (this.checkeVsibke === true) {
        setTimeout(() => {
          this.typeVisible = true;
        }, 1000);
      }
    },
    watch: {
      proposer: {
        handler(val, oldVal) {
          this.$store.commit('proposer', val);
        },
        deep: true
      }
    },
    components: {
      list,
      Register,
      handsontable
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .application
    height: 100%
    overflow: hidden
    .app_table
      float: left
      width: 74.6%
      min-width: 776px
      padding: 0 8px
      font-size: 14px
      overflow: hidden
      .header
        width: 100%
        display: flex
        li
          display: flex
          flex: 1
          height: 74px
          .title
            display: inline-block
            padding: 0 6px
            margin-top: 30px
            font-family: '微软雅黑'
            font-size: 14px
            font-weight: 500
            color: #3a3a3a
            vertical-align: middle
          .el-select
            min-width: 142px
            margin-top: 20px
          .ipt_text
            border: 1px solid #DFE6EC
            height: 34px
            text-indent: 10px
            margin-top: 20px
          .el-input__inner
            border: 1px solid #DFE6EC
            height: 36px
            border-radius: 0
      .proposer
        width: 100%
        margin-bottom: 10px
        background: #fff
        overflow: hidden
        .el-input__inner
          border: none
          height: 28px
          border-radius: 0
        .inner
          .table
            float: left
            border: 1px solid #DFE6EC
            width: 100%
            text-align: center
            th
              border-bottom: 1px solid #DFE6EC
              padding: 8px
              min-width: 70px
              vertical-align: middle
              background: #eef2f6
              .img_point
                width: 12px
                height: 12px
                margin-right: 6px
                vertical-align: middle
            td
              padding: 10px
              border-bottom: 1px solid #DFE6EC
              border-right: 1px solid #DFE6EC
              min-width: 70px
              vertical-align: middle
              overflow: hidden
              @media all and (min-width: 1370px)
                padding: 10px
              .proposer_situation
                display: inline-block
                width: 15px
              .proposer_add
                float: right
                padding: 0 6px
                color: #20A0FF;
                font-size: 12px
                cursor: pointer
        .sqrqk_caozuo a
          padding: 6px
          color: #20a0ff;
          font-size: 12px
      .mortgage
        width: 100%
        background: #fff
        margin-bottom: 10px
        .el-input__inner
          border: none
          height: 28px
          border-radius: 0
        .table
          width: 100%
          border: 1px solid #DFE6EC
          th
            border-bottom: 1px solid #DFE6EC;
            padding: 8px;
            min-width: 70px
            vertical-align: middle
            background: #eef2f6
            .img_point
              width: 12px
              height: 12px
              margin-right: 6px
              margin-top: -1px
              vertical-align: middle
          td
            padding: 10px;
            border-bottom: 1px solid #DFE6EC
            border-right: 1px solid #DFE6EC
            min-width: 70px
            vertical-align: middle
            overflow: hidden
            .table_1
              width: 100%
            .table_2
              width: 100%
            .table_1 tr
              &:last-child td
                border-bottom: 1px solid #fff
            .table_2 td
              border-bottom: 1px solid #fff
        .el-input__inner
          border-radius: 0
          border: none
          border-left: 1px solid #dfe6ec
      .td_text
        padding: 0
        font-size: 0
        vertical-align: middle
        .input_text
          width: 100%
          padding: 8px 0
          text-indent: 6px
          font-size: 14px
          line-height: 14px
          white-space: nowrap
          overflow: hidden
          text-overflow: ellipsis
      .infor
        margin-left: 6px
        font-size: 14px
        vertical-align: middle
        color: #20A0FF
        cursor: pointer
    .app_list
      float: left
      width: 23%
      height: 100%
      margin-left: 0px
      min-width: 270px
    .dialog
      .el-dialog__body
        padding: 12px 30px 10px 36px
        .demo-ruleForm
          width: 660px
          overflow: hidden
          .el-form-item
            display: inline-block
            .el-form-item__content
              width: 212px

        .el-input__inner
          height: 32px
      .el-table td, .el-table th
        height: 32px
</style>

