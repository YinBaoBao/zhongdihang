<template>
  <div>
    <div class="application">
      <div class="app_table">
        <div class="header">
          <table class="table" cellpadding="10" cellspacing="0">
            <tr>
              <td>登记类型</td>
              <td class="td_text" style="padding: 0;">
                <input v-model="djlx.djlxmc" class="input_text" type="text" value="" placeholder="">
              </td>
              <td>登记子类型</td>
              <td class="td_text" style="padding: 0;">
                <input v-model="djlx.djzlxmc" class="input_text" type="text" value="" placeholder="">
              </td>
              <td>报件编号</td>
              <td class="td_text" style="padding: 0;">
                <input v-model="$store.state.Bjbh" class="input_text" type="text" value="" placeholder="">
              </td>
              <td>经办人姓名</td>
              <td class="td_text" style="padding: 0;width: 40px;">
                <input class="input_text" type="text" value="" placeholder=""
                       style="width: 90px;">
              </td>
            </tr>
          </table>
        </div>
        <div class="border-height"></div>
        <div class="proposer">
          <div class="inner">
            <table class="table">
              <tr>
                <td :rowspan="10" style="padding: 0;min-width: 44px;vertical-align: middle;"><span
                  class="proposer_situation">申请人情况</span></td>
                <td></td>
                <td>姓名/名称</td>
                <td>证件类型</td>
                <td>证件号</td>
                <td>联系地址</td>
                <td>电话</td>
              </tr>
              <tr v-for="(item,index) in proposer.qlrs">
                <td>权利人
                  <span class="el-icon-plus proposer_add" @click="_application_add('权利人',proposer.qlrs)"></span>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.qlrmc"
                            readonly="readonly"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.qlrzjzlmc"
                            readonly="readonly"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.qlrzjh"
                            readonly="readonly"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.qlrdz"
                            readonly="readonly"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.qlrdh"
                            readonly="readonly"></textarea>
                </td>
              </tr>
              <tr v-if="proposer.qlrdlr">
                <td style="min-width: 110px;">权利人代理人
                  <span class="el-icon-plus proposer_add" @click="_add_dlr('权利人代理人')"></span>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.qlrdlr.qlrdlrmc"
                            readonly="readonly"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.qlrdlr.qlrdlrzjzl"
                            readonly="readonly"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.qlrdlr.qlrdlrzjh"
                            readonly="readonly"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.qlrdlr.qlrdlrdz"
                            readonly="readonly"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.qlrdlr.qlrdlrdh"
                            readonly="readonly"></textarea>
                </td>
              </tr>
              <tr v-for="(item,index) in proposer.ywrs">
                <td>义务人
                  <span class="el-icon-plus proposer_add" @click="_application_add('义务人',proposer.ywrs)"></span>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.ywrmc"
                            readonly="readonly"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.ywrzjzlmc"
                            readonly="readonly"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.ywrzjh"
                            readonly="readonly"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.ywrdz"
                            readonly="readonly"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.ywrdh"
                            readonly="readonly"></textarea>
                </td>
              </tr>
              <tr v-if="proposer.ywrdlr">
                <td>义务人代理人
                  <span class="el-icon-plus proposer_add" @click="_add_dlr('义务人代理人')"></span>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.ywrdlr.ywrdlrmc"
                            readonly="readonly"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.ywrdlr.ywrdlrzjzl"
                            readonly="readonly"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.ywrdlr.ywrdlrzjh"
                            readonly="readonly"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.ywrdlr.ywrdlrdz"
                            readonly="readonly"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.ywrdlr.ywrdlrdh"
                            readonly="readonly"></textarea>
                </td>
              </tr>
            </table>
          </div>
        </div>
        <div class="border-height"></div>
        <div class="mortgage">
          <table class="table">
            <tr>
              <td rowspan="5" style="padding: 0;min-width: 34px;vertical-align: middle;text-align: center;">
                <span class="mortgage_situation">抵押情况</span>
              </td>
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
                  style="width: 140px;margin-left: -1px;">
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
                  style="width: 140px;margin-left: -1px;">
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
                  style="width: 140px;margin-left: -1px;">
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
                      <input v-model="mortgage.bdcpgjg" class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;">
                    </td>
                    <td style="min-width: 90px;">土地评估价格</td>
                    <td class="td_text" style="padding: 0;">
                      <input v-model="mortgage.tdpgjg" class="input_text" type="text" placeholder="万元"
                             style="width: 80px;">
                    </td>
                    <td style="min-width: 86px;">房产评估价格</td>
                    <td class="td_text" style="padding: 0;">
                      <input v-model="mortgage.fcpgjg" class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;">
                    </td>
                    <td>贷款账号</td>
                    <td class="td_text" style="padding: 0;border-right: none;">
                      <input v-model="mortgage.dkzh" class="input_text" type="text" placeholder=""
                             style="width: 108px;">
                    </td>
                  </tr>
                  <tr>
                    <td>担保范围</td>
                    <td class="td_text" style="padding: 0;">
                      <input v-model="mortgage.dbfw" class="input_text" type="text"
                             placeholder="万元"
                             style="width: 82px;">
                    </td>
                    <td>在建建筑物抵押范围</td>
                    <td class="td_text" style="padding: 0;">
                      <input v-model="mortgage.zjjzwdyfw" class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;">
                    </td>
                    <td>房产抵押面积</td>
                    <td class="td_text" style="padding: 0;">
                      <input v-model="mortgage.fcdymj" class="input_text" type="text"
                             placeholder="万元"
                             style="width: 82px;">
                    </td>
                    <td>土地抵押面积</td>
                    <td class="td_text" style="padding: 0;">
                      <input v-model="mortgage.tddymj" class="input_text" type="text"
                             placeholder="平方米"
                             style="width: 108px;">
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
                      <el-select @change="_dyfschange" v-model="dyfsvalue" placeholder="请选择"
                                 style="width: 160px;margin-left: -1px;">
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
        <div class="border-height"></div>
        <div class="mortgage">
          <table class="table">
            <tr>
              <td rowspan="8" style="padding: 0;min-width: 34px;vertical-align: middle;text-align: center;"><span
                class="mortgage_situation">不动产情况</span></td>
              <td colspan="">坐落</td>
              <td class="td_text" style="padding: 0;">
                <input v-model="bdcqk.zl" class="input_text" type="text" placeholder="" value="">
              </td>
              <td>不动产单元号</td>
              <td class="td_text" style="padding: 0;">
                <input v-model="bdcqk.bdcdyh" class="input_text" type="text" placeholder="">
              </td>
            </tr>
            <tr>
              <td colspan="" style="min-width: 164px;">原不动产权证书号(证明号)</td>
              <td class="td_text" style="padding: 0;">
                <input v-model="bdcqk.bdcqzshy" class="input_text" type="text" placeholder="" value="">
              </td>
              <td style="min-width: 164px;">新不动产权证书号(证明号)</td>
              <td class="td_text" style="padding: 0;">
                <input v-model="bdcqk.bdcqzshx" class="input_text" type="text" placeholder="" value="">
              </td>
            </tr>
            <tr>
              <td colspan="">房屋用途</td>
              <td class="td_text" style="padding: 0;">
                <el-select @change="_fwytchange" v-model="bdcqk.fwyt" placeholder="请选择"
                           style="width: 214px;margin-left: -1px;">
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
                <el-select @change="_tdytchange" v-model="bdcqk.tdyt" placeholder="请选择"
                           style="width: 214px;margin-left: -1px;">
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
                  style="width: 214px; margin-left: -1px;">
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
                  style="width: 214px; margin-left: -1px;">
                </el-date-picker>
              </td>
            </tr>
            <tr>
              <td style="min-width: 58px;">权利性质</td>
              <td class="td_text" style="padding: 0;">
                <el-select @change="_qlxzchange" v-model="bdcqk.qlxz" placeholder="请选择"
                           style="width: 214px;margin-left: -1px;">
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
                <el-select @change="_ssqxchange" v-model="ssqxvalue" placeholder="请选择"
                           style="width: 214px;margin-left: -1px;">
                  <el-option
                    v-for="item in ssqxptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </td>
            </tr>
          </table>
        </div>
      </div>
      <div class="app_list">
        <list @appLook="Look_reg" @apply_submit="_apply_submit" @systemdata="systemdata"
              :upDatalist="upDatalist"></list>
      </div>
      <div class="dialog">
        <el-dialog :title="typename" :visible.sync="typeVisible" :modal="false" :close-on-click-modal="false"
                   size="000">
          <el-cascader :options="Typeoption"
                       @active-item-change="handleItemChange"
                       @change="_checked"
                       :props="props"
                       style="width: 300px;margin: 12px 12px;">
          </el-cascader>
          <div slot="footer" class="dialog-footer">
            <el-button @click="typeVisible = false">取 消</el-button>
            <el-button type="primary" @click="_typeoption_submit">确 定</el-button>
          </div>
        </el-dialog>
        <el-dialog title="登记申请书" :visible.sync="regVisible" size="000" top="10%">
          <Register v-if="regVisible" :Propose="proposer" :mortgage="mortgage" :bdcqk="bdcqk" :bjbh="bjbh"></Register>
          <span slot="footer" class="dialog-footer">
            <el-button @click="Close">取 消</el-button>
            <el-button type="primary" @click="regVisible = false">确 定</el-button>
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
        <el-dialog title="添加代理人" :visible.sync="add_dlr" size="000" top="6%" :modal="false"
                   :close-on-click-modal="false">
          <el-form :model="AddForm" :rules="rules" ref="AddForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="姓名或名称" prop="username">
              <el-input v-model="AddForm.username"></el-input>
            </el-form-item>
            <el-form-item label="证件类型" prop="zjlxvalue">
              <el-select v-model="zjlxvalue" placeholder="请选择" @change="_zjlxchange">
                <el-option
                  v-for="item in AddForm.zjlx"
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
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import list from '../list/list.vue';
  import Register from '../../components/dialog/Register.vue';
  import handsontable from '../../components/dialog/handsontable.vue';
  const ERR_OK = 0;

  export default {
    props: {},
    data() {
      return {
        typeVisible: false,    // 级联选择
        regVisible: false,     //  查看登记申请书
        handVisible: false,    //  申请人情况添加
        handTitle: '添加权利人',          //  添加权利人
        Handdata: '',           // 添加权利人对应数据
        Titlestate: '',          //  添加人名情况
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
        Typeoption: [  // 二级选项
          {
            label: '',
            cities: [],
            code: ''
          }
        ],
        props: {
          value: 'label',
          children: 'cities'
        },
        dyfsoptions: [
          {
            value: '1',
            label: '一般抵押'
          }, {
            value: '2',
            label: '最高额抵押'
          }
        ],
        dyfsvalue: '',
        dyfscode: '',
        fwytoptions: [
          {
            value: '',
            label: ''
          }, {
            value: '',
            label: ''
          }
        ],
        fwytvalue: '',
        fwytcode: '',
        tdytoptions: [
          {
            value: '',
            label: ''
          }, {
            value: '',
            label: ''
          }
        ],
        tdytvalue: '',
        tdytcode: '',
        qlxzoptions: [
          {
            value: '',
            label: ''
          }, {
            value: '',
            label: ''
          }
        ],
        qlxzvalue: '',
        qlxzcode: '',
        ssqxptions: [
          {
            value: '',
            label: ''
          }, {
            value: '',
            label: ''
          }
        ],
        ssqxvalue: '',
        ssqxcode: '',
        application: {},
        djlx: {},
        bjbh: '', // 报件编号
        proposer: {
          qlrs: [
            {
              bjbh: '201708001000001',
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
        upDatalist: [
          {
            bjbh: '',
            mlxh: '',
            mlmc: '不动产登记申请书',
            mlwjlx: '',
            mlwjsl: '1',
            state: ''
          },
          {
            bjbh: '',
            mlxh: '',
            mlmc: '申请人身份证明',
            mlwjlx: '',
            mlwjsl: '1',
            state: ''
          },
          {
            bjbh: '',
            mlxh: '',
            mlmc: '不动产权证书',
            mlwjlx: '',
            mlwjsl: '2',
            state: ''
          }
        ],  // 需要上的资料
        selectcode: '',  // 登记类型编号
        typename: '',  // 登记类型
        add_dlr: false, // 添加代理人
        AddForm: {
          username: '',
          zjh: '',
          zjlx: [
            {
              code: '',
              value: ''
            }
          ],
          address: '',
          telephone: ''
        },
        zjlxvalue: '',
        qlrzjlxcode: '',
        ywrzjlxcode: '',
        rules: {
          username: [
            {required: true, message: '请输入姓名或名称', trigger: 'change'}
          ],
//          zjlxvalue: [
//            {required: true, message: '请选择证件类型', trigger: 'change'}
//          ],
          zjh: [
            {required: true, message: '请输入证件号', trigger: 'change'}
          ],
          address: [
            {required: true, message: '请输入地址', trigger: 'change'}
          ],
          telephone: [
            {required: true, message: '请输入手机号', trigger: 'change'}
          ]
        }
      };
    },
    methods: {
      Look_reg() {
        this.regVisible = true;
      },
      systemdata() {  // 获取系统数据
        this.$http.get(this.$store.state.api + '/application').then((response) => {
          response = response.body;
          if (response.errno === ERR_OK) {
            this.application = response.data;
          }
        });
      },
      _application_add(title, event) {    // 申请人情况添加
        this.Handdata = event;
        this.handVisible = true;
        this.handTitle = '添加' + title;
        this.Titlestate = title;
      },
      _add_dlr(title) {  // 添加代理人
        this.AddForm.username = '';
        this.AddForm.zjh = '';
        this.AddForm.address = '';
        this.AddForm.telephone = '';
        this.zjlxvalue = '';
        this.Titlestate = title;
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
                this.proposer.qlrdlr.qlrdlrzjzl = this.zjlxvalue;
                break;
              case '义务人代理人':
                this.proposer.ywrdlr.ywrdlrmc = this.AddForm.username;
                this.proposer.ywrdlr.ywrdlrzjh = this.AddForm.zjh;
                this.proposer.ywrdlr.ywrdlrdz = this.AddForm.address;
                this.proposer.ywrdlr.ywrdlrdh = this.AddForm.telephone;
                this.proposer.ywrdlr.ywrdlrzjzl = this.zjlxvalue;
                break;
            }
            this.add_dlr = false;
          }
        });
      },
      getzjlx() {    // 获取证件种类
        let token = localStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          access_token: token,
          code: 130
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
            this.AddForm.zjlx = arr;
          }
        });
      },
      _zjlxchange(val) {
        let options = this.AddForm.zjlx;
        for (var i = 0; i < options.length; i++) {
          if (val.indexOf(options[i].value) > -1) {
            switch (this.Titlestate) {
              case '权利人代理人':
                this.qlrzjlxcode = options[i].code;
                break;
              case '义务人代理人':
                this.ywrzjlxcode = options[i].code;
                break;
            }
          }
        }
      },
      getdyfs() {    // 获取抵押方式
        let token = localStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          access_token: token,
          code: 10006
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                value: data[i].code,
                label: data[i].name
              };
              arr.push(json);
            }
            this.dyfsoptions = arr;
          }
        });
      },
      _dyfschange(val) {
        this.dyfscode = val;
      },
      gettdyt() {    // 获取土地用途下拉列表
        let token = localStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          access_token: token,
          code: 10008
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                value: data[i].code,
                label: data[i].name
              };
              arr.push(json);
            }
            this.tdytoptions = arr;
          }
        });
      },
      _tdytchange(val) {
        this.tdytcode = val;
      },
      getfwyt() {    // 获取房屋用途下拉列表
        let token = localStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          access_token: token,
          code: 117
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                value: data[i].code,
                label: data[i].name
              };
              arr.push(json);
            }
            this.fwytoptions = arr;
          }
        });
      },
      _fwytchange(val) {
        this.fwytcode = val;
      },
      getqlxz() {    // 获取权利性质下拉列表
        let token = localStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          access_token: token,
          code: 108
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                value: data[i].code,
                label: data[i].name
              };
              arr.push(json);
            }
            this.qlxzoptions = arr;
          }
        });
      },
      _qlxzchange(val) {
        this.qlxzcode = val;
      },
      getssqx() {    // 获取所属区县下拉列表
        let token = localStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          access_token: token,
          code: '10002',
          pcode: ''
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                value: data[i].code,
                label: data[i].name
              };
              arr.push(json);
            }
            this.ssqxptions = arr;
          }
        });
      },
      _ssqxchange(val) {
        this.ssqxcode = val;
      },
      typeoption_1(code) {  // 获取一级表单数据
        let token = localStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          access_token: token,
          code: code
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                code: data[i].code,
                subcode: data[i].subclass,
                label: data[i].name,
                cities: []
              };
              arr.push(json);
            }
            this.Typeoption = arr;
          }
        });
      },
      typeoption_2(index, code) {  // 获取二级表单数据
        let token = localStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: '10000',
          pcode: code,
          access_token: token
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                code: data[i].code,
                label: data[i].name,
                cities: []
              };
              arr.push(json);
            }
            this.Typeoption[index].cities = arr;
          }
        });
      },
      typeoption_3(index1, index2) {  // 获取三级表单数据
        let token = localStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: '10002',
          pcode: '',
          access_token: token
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
            this.Typeoption[index1].cities[index2].cities = arr;
          }
        });
      },
      handleItemChange(val) {  // 级联选择
//        console.log('active item:', val);
        let options = this.Typeoption;
        setTimeout(_ => {
          for (var i = 0; i < options.length; i++) {
            if (val.indexOf(options[i].label) > -1 && !options[i].cities.length) {
              this.typeoption_2(i, options[i].code);
            }
          }
          for (var k = 0; k < options.length; k++) {
            for (var j = 0; j < options[k].cities.length; j++) {
              if (val.indexOf(options[k].cities[j].label) > -1) {
                this.typeoption_3(k, j);
              }
            }
          }
        }, 300);
      },
      _checked(val) {  // 保存选项编码
        let options = this.Typeoption;
        let arr = [];
        for (var i = 0; i < options.length; i++) {
          if (val.indexOf(options[i].label) > -1) {
            arr.push(options[i].code);
            for (var k = 0; k < options[i].cities.length; k++) {
              if (val.indexOf(options[i].cities[k].label) > -1) {
                arr.push(options[i].cities[k].code);
                for (var h = 0; h < options[i].cities[k].cities.length; h++) {
                  if (val.indexOf(options[i].cities[k].cities[h].label) > -1) {
                    arr.push(options[i].cities[k].cities[h].code);
                  }
                }
              }
            }
          }
        }
        this.selectcode = arr;
      },
      _typeoption_submit() {  // 申请报件
//        console.log(this.selectcode);
        let token = localStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/BDCDJSQControl/applyBJBH', {
          jkzh: 200,
          access_token: token,
          bjssqx: this.selectcode[2],
          djlx: this.selectcode[0],
          djsjlx: this.selectcode[1]
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
            this.upDatalist = this.application.body.qyclmls;
          } else {
            this.$message.error('暂无数据');
          }
          this.typeVisible = false;
        });
      },
      _Keephand() { // 保存权利人信息
        this.handVisible = false;
      },
      _apply_submit() {  // 申请提交
        if (this.dyfscode === '') {
          this.$message({
            message: '请选择抵押方式',
            type: 'warning'
          });
          return false;
        }
        let token = localStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/BDCDJSQControl/submitDJSQ', {
          jkzh: 200,
          bjbh: this.bjbh,
          access_token: token,
          qlrdlrmc: this.proposer.qlrdlr.qlrdlrmc,
          qlrdlrzjzl: this.qlrzjlxcode,
          qlrdlrzjh: this.proposer.qlrdlr.qlrdlrzjh,
          qlrdlrdh: this.proposer.qlrdlr.qlrdlrdh,
          qlrdlrdz: this.proposer.qlrdlr.qlrdlrdz,
          ywrdlrmc: this.proposer.ywrdlr.ywrdlrmc,
          ywrdlrzjzl: this.ywrzjlxcode,
          ywrdlrzjh: this.proposer.ywrdlr.ywrdlrzjh,
          ywrdlrdh: this.proposer.ywrdlr.ywrdlrdh,
          ywrdlrdz: this.proposer.ywrdlr.ywrdlrdz,
          bdcdyh: this.bdcqk.bdcdyh,
          bdcqzhy: this.bdcqk.bdcqzshy,
          bdcqzhx: this.bdcqk.bdcqzshx,
          zl: this.bdcqk.zl,
          ssqx: this.ssqxcode,  // 所属区县
          fwyt: this.fwytcode,
          fwmj: this.bdcqk.fwmj,
          tdyt: this.bdcqk.tdyt,
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
          sfgy: '1',  // 是否共有
          gyfs: '1',
          afgyfeqk: '11',
          sffbcz: '1',
          qtsm: '11'
        }).then((response) => {
          response = response.body;
          switch (response.status) {
            case '200':
              this.$message({
                message: '提交成功，报件已处于待受理状态。',
                type: 'success'
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
      }
    },
    created() {
      this.$nextTick(() => {
        let token = localStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdfl', {
          access_token: token
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            this.code = response.body[1].code;
            this.typename = '请选择' + response.body[1].name;
            this.typeoption_1(this.code);
          }
        });
        this.getdyfs();
        this.getfwyt();
        this.gettdyt();
        this.getqlxz();
        this.getssqx();
      });
    },
    mounted() {
      this.typeVisible = true;
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
    .app_table
      float: left
      width: 74%
      min-width: 776px
      font-size: 14px
      overflow: hidden
      .header
        width: 100%
        .table
          width: 100%
          border: 1px solid #DFE6EC
          text-align: center
          td
            padding: 6px;
            border-bottom: 1px solid #DFE6EC;
            border-right: 1px solid #DFE6EC;
            min-width: 70px
            vertical-align: middle
      .proposer
        width: 100%
        height: 148px
        overflow: auto
        .inner
          min-height: 200px
          .table
            float: left
            border: 1px solid #DFE6EC
            width: 100%
            margin-top: 4px
            text-align: center
            td
              padding: 6px;
              border-bottom: 1px solid #DFE6EC;
              border-right: 1px solid #DFE6EC;
              min-width: 70px
              vertical-align: middle
              .proposer_situation
                display: inline-block
                width: 15px
              .proposer_add
                display: inline-block
                margin-left: 5px
                color: #20A0FF;
                font-size: 12px
                cursor: pointer
      .mortgage
        width: 100%
        .table
          width: 100%
          border: 1px solid #DFE6EC
          .mortgage_situation
            display: inline-block
            width: 15px
          td
            padding: 6px;
            border-bottom: 1px solid #DFE6EC
            border-right: 1px solid #DFE6EC
            min-width: 70px
            vertical-align: middle
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
        .textarea
          width: 100%
          border: none
          padding: 2px 0
          text-indent: 6px
          font-size: 12px
          resize: none
        .input_text
          width: 100%
          padding: 8px 0
          text-indent: 6px
          font-size: 14px
      .el-input__inner
        border: none
        border-radius: 0
    .app_list
      float: left
      width: 26%
      min-width: 290px
    .dialog
      .el-dialog__body
        padding: 12px 30px 20px 36px
        .demo-ruleForm
          width: 660px
          overflow: hidden
          .el-form-item
            display: inline-block
            .el-form-item__content
              width: 212px

</style>
