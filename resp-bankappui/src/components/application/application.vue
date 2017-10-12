<template>
  <div>
    <div class="application">
      <div class="app_table">
        <div class="header">
          <table class="table" cellpadding="10" cellspacing="0">
            <tr>
              <td>登记类型</td>
              <td class="td_text" style="padding: 0;width: 40px;">
                <input v-model="djlx.djlxmc" class="input_text" type="text" value="" placeholder=""
                       style="width: 90px;">
              </td>
              <td>登记子类型</td>
              <td class="td_text" style="padding: 0;width: 40px;">
                <input v-model="djlx.djzlxmc" class="input_text" type="text" value="" placeholder=""
                       style="width: 90px;">
              </td>
              <td>报件编号</td>
              <td class="td_text" style="padding: 0;width: 40px;">
                <input v-model="bjbh" class="input_text" type="text" value="" placeholder=""
                       style="width: 120px;">
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
                <td :rowspan="10" style="padding: 0;min-width: 27px;vertical-align: middle;"><span
                  class="proposer_situation">申请人情况</span></td>
                <td></td>
                <td>姓名/名称</td>
                <td>证件类型</td>
                <td>证件号</td>
                <td>联系地址</td>
                <td>电话</td>
              </tr>
              <tr v-if="proposer.qlrs" v-for="(item,index) in proposer.qlrs">
                <td>权利人
                  <span class="el-icon-plus proposer_add" @click="_application_add(index,'权利人')"></span>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.qlrmc"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.qlrzjzlmc"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.qlrzjh"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.qlrdz"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.qlrdh"></textarea>
                </td>
              </tr>
              <tr v-if="proposer.qlrdlr">
                <td>代理人
                  <span class="el-icon-plus proposer_add" @click="_application_add(index,'代理人')"></span>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.qlrdlr.qlrmc"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.qlrdlr.qlrzjzlmc"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.qlrdlr.qlrzjh"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.qlrdlr.qlrdz"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.qlrdlr.qlrdh"></textarea>
                </td>
              </tr>
              <tr v-if="proposer.ywrdlr">
                <td>义务人
                  <span class="el-icon-plus proposer_add" @click="_application_add(index,'义务人')"></span>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.ywrdlr.qlrmc"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.ywrdlr.qlrzjzlmc"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.ywrdlr.qlrzjh"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.ywrdlr.qlrdz"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="proposer.ywrdlr.qlrdh"></textarea>
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
              <td colspan="">被担保债权数额</td>
              <td class="td_text" style="padding: 0;width: 40px;">
                <input v-model="mortgage.bdbzqse" value="万元" class="input_text" type="text" placeholder="万元">
              </td>
              <td>抵押合同签署日期</td>
              <td class="td_text" style="padding: 0;width: 40px;">
                <el-date-picker
                  v-model="mortgage.dyhtqdrq"
                  type="date"
                  placeholder="选择日期"
                  :picker-options="pickerOptions"
                  style="width: 122px; margin-left: -1px;">
                </el-date-picker>
              </td>
              <td style="">银行抵押业务号</td>
              <td class="td_text" style="padding: 0;width: 40px;">
                <input v-model="mortgage.yhdyywh" class="input_text" type="text" placeholder="">
              </td>
            </tr>
            <tr>
              <td colspan="">债务履行开始时间</td>
              <td class="td_text" style="padding: 0;">
                <el-date-picker
                  v-model="mortgage.zwlxqssj"
                  type="date"
                  placeholder="选择日期"
                  :picker-options="pickerOptions"
                  style="width: 122px; margin-left: -1px;">
                </el-date-picker>
              </td>
              <td>债务履行结束时间</td>
              <td class="td_text" style="padding: 0;">
                <el-date-picker
                  v-model="mortgage.zwlxjssj"
                  type="date"
                  placeholder="选择日期"
                  :picker-options="pickerOptions"
                  style="width: 122px; margin-left: -1px;">
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
                    <td>不动产价格</td>
                    <td class="td_text" style="padding: 0;">
                      <input v-model="mortgage.bdcpgjg" class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;">
                    </td>
                    <td>土地价格</td>
                    <td v-model="mortgage.tdpgjg" class="td_text" style="padding: 0;">
                      <input class="input_text" type="text" placeholder="万元"
                             style="width: 80px;">
                    </td>
                    <td>房产价格</td>
                    <td class="td_text" style="padding: 0;">
                      <input v-model="mortgage.fcpgjg" class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;">
                    </td>
                    <td>贷款账号</td>
                    <td class="td_text" style="padding: 0;border-right: none;">
                      <input v-model="mortgage.dkzh" class="input_text" type="text" placeholder=""
                             style="width: 110px;">
                    </td>
                  </tr>
                  <tr>
                    <td>担保范围</td>
                    <td class="td_text" style="padding: 0;">
                      <input v-model="mortgage.dbfw" class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;height: 30px;">
                    </td>
                    <td>在建建筑物抵押范围</td>
                    <td class="td_text" style="padding: 0;">
                      <input v-model="mortgage.zjjzwdyfw" class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;height: 30px;">
                    </td>
                    <td>房产抵押面积</td>
                    <td class="td_text" style="padding: 0;">
                      <input v-model="mortgage.fcdymj" class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;height: 30px;">
                    </td>
                    <td>土地抵押面积</td>
                    <td class="td_text" style="padding: 0;">
                      <input v-model="mortgage.tddymj" class="input_text" type="text"
                             placeholder="平方米"
                             style="width: 110px;height: 30px;">
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
            <tr>
              <td colspan="8" style="padding: 0;">
                <table class="table_2" cellpadding="10" cellspacing="0" style="width: 100%;">
                  <tr>
                    <td>债权人</td>
                    <td class="td_text" style="padding: 0;">
                      <input v-model="mortgage.zqr" class="input_text" type="text" placeholder=""
                             value="中国建设银行股份有限公司苏州分行"
                             style="width: 294px">
                    </td>
                    <td>债务人</td>
                    <td class="td_text" style="padding: 0;border-right: none;">
                      <input v-model="mortgage.zwr" class="input_text" type="text" placeholder=""
                             value=""
                             style="width: 296px">
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
              <td rowspan="5" style="padding: 0;min-width: 34px;vertical-align: middle;text-align: center;"><span
                class="mortgage_situation">不动产情况</span></td>
              <td colspan="">坐落</td>
              <td class="td_text" style="padding: 0;">
                <input v-model="bdcqk.zl" class="input_text" type="text" placeholder="" value="" style="width: 260px;">
              </td>
              <td>不动产单元号</td>
              <td class="td_text" style="padding: 0;">
                <input v-model="bdcqk.bdcdyh" class="input_text" type="text" placeholder="" value=""
                       style="width: 262px;">
              </td>
            </tr>
            <tr>
              <td colspan="">不动产权证明号</td>
              <td class="td_text" style="padding: 0;">
                <input v-model="bdcqk.bdcqzshy" class="input_text" type="text" placeholder="" value=""
                       style="width: 260px;">
              </td>
              <td>不动产权属证书号</td>
              <td class="td_text" style="padding: 0;">
                <input v-model="bdcqk.bdcqzshx" class="input_text" type="text" placeholder="" value=""
                       style="width: 262px;">
              </td>
            </tr>
            <tr>
              <td colspan="4" style="padding: 0;">
                <table class="table_1" cellpadding="10" cellspacing="0">
                  <tr>
                    <td style="min-width: 100px;">土地使用起始日</td>
                    <td class="td_text" style="padding: 0;">
                      <el-date-picker
                        v-model="bdcqk.tdsyqssj"
                        type="date"
                        placeholder="选择日期"
                        :picker-options="pickerOptions"
                        style="width: 118px; margin-left: -1px;">
                      </el-date-picker>
                    </td>
                    <td style="min-width: 100px;">土地使用结束日</td>
                    <td class="td_text" style="padding: 0;">
                      <el-date-picker
                        v-model="bdcqk.tdsyjssj"
                        type="date"
                        placeholder="选择日期"
                        :picker-options="pickerOptions"
                        style="width: 118px; margin-left: -1px;">
                      </el-date-picker>
                    </td>
                    <td style="min-width: 58px;">权利性质</td>
                    <td class="td_text" style="padding: 0;">
                      <input v-model="bdcqk.qlxz" class="input_text" type="text" placeholder="" value=""
                             style="width: 76px;">
                    </td>
                    <td style="min-width: 58px;">所属区县</td>
                    <td class="td_text" style="padding: 0;border-right: none;">
                      <input v-model="bdcqk.ssqx" class="input_text" type="text" placeholder="" value=""
                             style="width: 78px;">
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
        </div>
      </div>
      <div class="app_list">
        <list @appLook="Look_reg" @systemdata="systemdata" :upDatalist="upDatalist"></list>
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
        <el-dialog title="登记申请书" :visible.sync="regVisible" size="000" top="10%" :before-close="handleClose">
          <Register v-if="regVisible" :Propose="application.body"></Register>
          <span slot="footer" class="dialog-footer">
            <el-button @click="Close">取 消</el-button>
            <el-button type="primary" @click="regVisible = false">确 定</el-button>
          </span>
        </el-dialog>
        <el-dialog :title="handTitle" :visible.sync="handVisible" size="000" top="10%" :before-close="handleClose">
          <handsontable v-if="handVisible" :Propose="proposer" :Title="Titlestate" :index="handIndex"
                        ref=Keephand></handsontable>
          <span slot="footer" class="dialog-footer">
            <el-button @click="Close">取 消</el-button>
            <el-button type="primary" @click="_Keephand">保 存</el-button>
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
        typeVisible: false,   // 级联选择
        regVisible: false,    //  查看登记申请书
        handVisible: false,    //  申请人情况添加
        handTitle: '添加权利人',          //  添加权利人
        Titlestate: '',          //  添加人名情况
        handIndex: null,
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
        application: {},
        djlx: {},
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
          qlrdlr: {
            qlrdlrdh: null,
            qlrdlrdz: null,
            qlrdlrmc: null,
            qlrdlrzjh: null,
            qlrdlrzjzl: null,
            qlrdlrzjzlmc: null
          },
          ywrdlr: {
            qlrdlrdh: null,
            qlrdlrdz: null,
            qlrdlrmc: null,
            qlrdlrzjh: null,
            qlrdlrzjzl: null,
            qlrdlrzjzlmc: null
          }
        },
        mortgage: {  // 抵押情况
          dyfs: null,
          dyfsmc: null,
          bdcpgjg: null,
          dbfw: null,
          dkywbh: null,
          dkzh: null,
          dyhtqdrq: null,
          fcdymj: null,
          fcpgjg: null,
          tddymj: null,
          tdpgjg: null,
          yhdyywh: null,
          bdbzqse: null,
          zjjzwdyfw: null,
          zqr: null,
          zwlxjssj: null,
          zwlxqssj: null,
          zwr: null
        },
        bdcqk: {    //  不动产情况
          bdcqzshy: null,
          bdcqzshx: null,
          bdcdyh: null,
          zl: null,
          ssqx: null,
          ssqxmc: null,
          fwyt: null,
          fwytmc: null,
          fwmj: null,
          tdyt: null,
          tdytmc: null,
          tdmj: null,
          tdsyqssj: null,
          tdsyjssj: null,
          qlxz: null,
          qlxzmc: null
        },
        upDatalist: [
          {
            bjbh: '',
            mlxh: null,
            mlmc: '',
            mlwjlx: '',
            mlwjsl: null,
            state: ''
          },
          {
            bjbh: '',
            mlxh: null,
            mlmc: '',
            mlwjlx: '',
            mlwjsl: null,
            state: ''
          },
          {
            bjbh: '',
            mlxh: null,
            mlmc: '',
            mlwjlx: '',
            mlwjsl: null,
            state: ''
          }
        ],  // 需要上的资料
        selectcode: '',  // 登记类型编号
        typename: ''  // 登记类型
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
      _application_add(index, event) {    // 申请人情况添加
        this.handVisible = true;
        this.handTitle = '添加' + event;
        this.Titlestate = event;
        this.handIndex = index;
      },
      typeoption_1(code) {  // 获取一级表单数据
        let token = sessionStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/TokrnControl/geyzdb', {
          access_token: token,
          code: code
        }).then((response) => {
          response = response.body;
          if (response.status === 200) {
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
        let token = sessionStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/TokrnControl/geyzdb', {
          code: '10000',
          pcode: code,
          access_token: token
        }).then((response) => {
          response = response.body;
          if (response.status === 200) {
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
        let token = sessionStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/TokrnControl/geyzdb', {
          code: '10002',
          pcode: '',
          access_token: token
        }).then((response) => {
          response = response.body;
          if (response.status === 200) {
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
      _typeoption_submit() {
//        console.log(this.selectcode);
        let token = sessionStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/BDCDJSQControl/applyBJBH', {
          jkzh: 200,
          access_token: token,
          bjssqx: this.selectcode[2],
          djlx: this.selectcode[0],
          djsjlx: this.selectcode[1]
        }).then((response) => {
          response = response.body;
          console.log(response);
          if (response.status === 200) {
            this.application = response.body;
            this.djlx = this.application.body.sqdjsy;
            this.bjbh = this.application.body.bjbh;
            this.proposer = this.application.body.sqrqk;
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
        this.$refs.Keephand.keep();
        this.handVisible = false;
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
      }
    },
    created() {
      let token = sessionStorage.getItem('login_token');
      this.$http.post(this.$store.state.Host + '/TokrnControl/getzdfl', {
        access_token: token
      }).then((response) => {
        response = response.body;
        if (response.status === 200) {
          this.code = response.body[1].code;
          this.typename = '请选择' + response.body[1].name;
          this.typeoption_1(this.code);
        }
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
      width: 72.1%
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
        .el-input__inner
          border-radius: 0
          border: none
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
          width: 128px
          border: none
          padding: 2px 4px
          vertical-align: middle
          line-height: 16px;
          font-size: 12px
          resize: none
        .input_text
          width: 108px
          padding: 9px 6px
          font-size: 14px
    .app_list
      float: left
      width: 27%
      min-width: 290px
    .dialog
      .el-dialog__body
        padding: 26px 30px 20px 36px
</style>
