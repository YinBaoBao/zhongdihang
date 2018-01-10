<template>
  <div class="houseinfo">
    <div class="info-header">
      <ul class="list">
        <li class="list-item">
          <span class="title">查询区域</span>
          <el-checkbox-group v-model="Areacheck">
            <el-checkbox v-if="Areaoption" v-for="item in Areaoption" :label="item.name" :key="item.id"></el-checkbox>
          </el-checkbox-group>
        </li>
        <li class="list-item">
          <span class="title">查询条件</span>
          <el-input v-if="Inputoption" v-for="item in Inputoption" :placeholder="`请输入${item.name}`"
                    value="" :key="item.id" @blur="_Iptblur" style="margin-right: 15px;"></el-input>
        </li>
        <li class="list-item">
          <span class="title">查看历史</span>
          <el-checkbox v-model="IsHistory"></el-checkbox>
        </li>
        <li class="list-item">
          <span class="title">授权方式</span>
          <span class="state">暂未获取授权方式</span>
          <el-upload class="upload-demo"
                     action="https://jsonplaceholder.typicode.com/posts/"
                     :show-file-list="false"
                     :on-success="_uploadsuccess"
                     :on-error="_uploaderror"
                     :before-upload="beforeAvatarUpload"
                     :on-progress="_uploadprogress"
                     :before-remove="_upbeforeRemove"
                     :on-remove="_uploadRemove">
            <el-button size="small" type="primary">上传授权委托书</el-button>
          </el-upload>
        </li>
      </ul>
      <div class="img_show">
        <img v-if="ImageUrl" class="imgs" :src="ImageUrl" alt="" @mouseenter="_enter">
        <transition name="img_fade">
          <div v-if="Imgmark" class="img_mark" @mouseleave="_leave">
            <i class="el-icon-zoom-in imgs_icon" @click="_large"></i>
            <i class="el-icon-delete imgs_icon" @click="_upbeforeRemove" style="margin-left: 6px;"></i>
          </div>
        </transition>
        <el-progress v-if="IsProgress" type="circle" :percentage="Percent" :status="upstatus"
                     :width="110"></el-progress>
        <el-dialog :visible.sync="Imgdialog" size="tiny">
          <img width="100%" :src="ImageUrl" alt="">
        </el-dialog>
      </div>
      <el-button class="seartch" type="primary">查询</el-button>
    </div>
    <div class="info-table">
      <div class="title">
        <span class="text">查询结果</span>
      </div>
      <el-table v-if="resultinfo" :data="InfoData">
        <el-table-column v-for="item in InfoColumn" :prop="item.prop" :label="item.label"
                         :key="item.id"></el-table-column>
        <el-table-column label="操作" width="80">
          <template slot-scope="scope">
            <el-button @click="_xiangqing(scope.$index,scope.row)" type="text">详情>></el-button>
          </template>
        </el-table-column>
      </el-table>
      <TJInfo v-else="resultinfo"></TJInfo>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import TJInfo from './TJInfo.vue';
  export default {
    data() {
      return {
        Areacheck: [],
        Areaoption: [],
        Inputoption: [],
        powername: '',  // 权利人姓名
        powerzjh: '',   // 权利人证件号
        bdcz: '',       // 不动产证
        IsHistory: false,  // 查看历史
        ImageUrl: '',
        Imgmark: false,    // 图片显示动画
        Imgdialog: false,   // 放大图片
        IsProgress: false,
        Percent: 0,          // 上传进度
        upstatus: '',        // 上传状态
        resultinfo: true,
        InfoData: [
          {
            index: '1',
            lx: '房地产权',
            qzh: '00631502',
            zl: '东湖大郡4期东城郡130幢701室',
            qlr: '李健、李延亮',
            dys: '1',
            cfs: '0',
            xzq: '园区'
          }
        ],
        InfoColumn: [
          {
            prop: 'index',
            label: '序号'
          },
          {
            prop: 'lx',
            label: '登记信息类型'
          },
          {
            prop: 'qzh',
            label: '权证号'
          },
          {
            prop: 'zl',
            label: '坐落'
          },
          {
            prop: 'qlr',
            label: '权利人'
          },
          {
            prop: 'dys',
            label: '抵押数'
          },
          {
            prop: 'cfs',
            label: '查封数'
          },
          {
            prop: 'xzq',
            label: '行政区'
          }
        ]
      };
    },
    computed: {
      inputval() {
        let val = this.InputVal;
        return val;
      }
    },
    methods: {
      _xiangqing(index, row) {
        console.log(row);
//        window.open(window.location.origin + '#/xq', '_blank');
      },
      _Iptblur(e) {      // 查询条件input
        let Name = e.target.placeholder;
        let Value = e.target.value;
        if (Name.indexOf('不动产证') > 0) {
          this.bdcz = Value;
        }
        if (Name.indexOf('权利人证件号') > 0) {
          this.powerzjh = Value;
        }
        if (Name.indexOf('权利人姓名') > 0) {
          this.powername = Value;
        }
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
      _uploadprogress(event, file, fileList) {  // 文件上传时
        this.upstatus = '';
        this.IsProgress = true;
        this.Percent = event.percent;
      },
      _uploadsuccess(res, file) {
        if (res.id === 101) {
          this.upstatus = 'success';
        }
        this.ImageUrl = URL.createObjectURL(file.raw);
        setTimeout(_ => {
          this.IsProgress = false;
        }, 1000);
      },
      _uploaderror(err, file, fileList) {
        console.log(err);
        this.upstatus = 'exception';
        setTimeout(_ => {
          this.IsProgress = false;
        }, 1000);
      },
      _upbeforeRemove(file, fileList) {  // 确定删除
        return this.$confirm(`确定移除${file.name}?`);
      },
      _uploadRemove() {  // 删除上传图片
      },
      _enter() {
        this.Imgmark = true;
      },
      _leave() {
        this.Imgmark = false;
      },
      _large() {
        this.Imgdialog = true;
      }
    },
    created() {
      this.$http.post('http://localhost:8081/api/seartch').then((response) => {
        let data = response.body.data;
        console.log(data);
        for (let i = 0; i < data.length; i++) {
          if (data[i].name === '查询区域') {
            this.Areaoption = data[i].roles;
          }
          if (data[i].name === '查询条件') {
            this.Inputoption = data[i].roles;
          }
        }
      });
    },
    watch: {},
    components: {
      TJInfo
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .houseinfo
    height: calc(100% - 80px)
    padding: 0 30px
    overflow: auto
    .info-header
      padding-top: 20px
      .list
        display: inline-block
        vertical-align: top
        .list-item
          height: 48px
          line-height: 48px
          .title
            display: inline-block
            padding-right: 12px
            font-family: '微软雅黑'
            font-size: 14px
            font-weight: 500
            vertical-align: middle
          .state
            font-family: '微软雅黑'
            font-size: 14px
            color: #C3C3C3
            vertical-align: middle
          .el-checkbox-group
            display: inline-block
            .el-checkbox
              margin-right: 10px
          .el-input
            display: inline-block
            width: 180px
            .el-input__inner
              height: 36px
          .upload-demo
            display: inline-block
            margin-left: 15px
      .img_show
        display: inline-block
        border: 1px solid #D9D9D9
        width: 110px
        height: 160px
        margin-top: 30px
        vertical-align: top
        position: relative
        .imgs
          position: absolute
          left: 0px
          top: 0
          width: 100%
          height: 100%
          cursor: pointer
        .img_mark
          position: absolute
          left: 0
          top: 0
          width: 100%
          height: 160px
          line-height: 160px
          background-color: rgba(0, 0, 0, .5)
          cursor: default
          color: #fff
          text-align: center
          &.img_fade-enter, &.img_fade-leave-active
            opacity: 0
          &.img_fade-enter-active, &.img_fade-leave-active
            transition: all 0.3s linear
          .imgs_icon
            padding: 4px
            font-size: 22px
            cursor: pointer
        .el-progress
          margin-top: 20px
      .seartch
        margin-left: 15px
        vertical-align: bottom
        padding: 10px 26px
    .info-table
      width: 100%
      padding-bottom: 20px
      margin-top: 20px
      margin-bottom: 10px
      background: #fff
      box-shadow: 0 0 10px rgba(69, 149, 255, 0.4)
      border-radius: 5px
      .title
        height: 40px
        line-height: 40px
        .text
          padding-left: 10px
          font-family: '微软雅黑'
          font-size: 16px
          font-weight: 600px
          color: #33363f
      .el-table th, .el-table td
        font-family: '微软雅黑'
        color: #656565
      .el-table th
        background: #F4F8F9
        border: none
      .el-table td
        padding: 4px 0
</style>
