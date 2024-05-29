<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="编号" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="期刊" prop="journal">
        <el-input
          v-model="queryParams.journal"
          placeholder="请输入期刊"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="题目" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入题目"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择类型" clearable>
          <el-option
            v-for="dict in dict.type.thesis_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="论文编号" prop="thesisNumber">
        <el-input
          v-model="queryParams.thesisNumber"
          placeholder="请输入论文编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="导师类别" prop="tutorType">
        <el-select v-model="queryParams.tutorType" placeholder="请选择导师类别" clearable>
          <el-option
            v-for="dict in dict.type.author_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="作者" prop="userId">
        <el-select v-model="queryParams.userId" filterable placeholder="请选择">
          <el-option
            v-for="item in userList"
            :key="item.userId"
            :label="item.userName"
            :value="item.userId"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="发布时间">
        <el-date-picker
          v-model="daterangePublishTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="发表级别" prop="publishLevel">
        <el-select v-model="queryParams.publishLevel" placeholder="请选择发表级别" clearable>
          <el-option
            v-for="dict in dict.type.thesis_level"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!--      <el-form-item label="依托项目" prop="relyProject">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.relyProject"-->
      <!--          placeholder="请输入依托项目"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item label="页码" prop="pageNumber">
        <el-input
          v-model="queryParams.pageNumber"
          placeholder="请输入页码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间">
        <el-date-picker
          v-model="daterangeUpdateTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['manage:thesisInfo:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['manage:thesisInfo:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['manage:thesisInfo:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['manage:thesisInfo:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="thesisInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="编号" align="center" prop="id"/>
      <el-table-column label="期刊" align="center" prop="journal"/>
      <el-table-column label="题目" align="center" prop="title"/>
      <el-table-column label="类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.thesis_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="论文编号" align="center" prop="thesisNumber"/>
      <el-table-column label="导师类别" align="center" prop="tutorType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.author_type" :value="scope.row.tutorType"/>
        </template>
      </el-table-column>
      <el-table-column label="作者" align="center" prop="userName"/>
      <el-table-column label="发布时间" align="center" prop="publishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发表级别" align="center" prop="publishLevel">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.thesis_level" :value="scope.row.publishLevel"/>
        </template>
      </el-table-column>
      <el-table-column label="依托项目" align="center" prop="relyProject"/>
      <el-table-column label="论文文件" align="center" prop="file">
        <template slot-scope="scope">
          <el-link v-show="scope.row.file!=null" :href="`${baseUrl}${scope.row.file}`" :underline="false"
                   target="_blank"
          >
            <span> 文件 </span>
          </el-link>
        </template>
      </el-table-column>
      <el-table-column label="页码" align="center" prop="pageNumber"/>
      <el-table-column label="创建人" align="center" prop="createBy"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:thesisInfo:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:thesisInfo:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改论文信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="期刊" prop="journal">
          <el-input v-model="form.journal" placeholder="请输入期刊"/>
        </el-form-item>
        <el-form-item label="题目" prop="title">
          <el-input v-model="form.title" placeholder="请输入题目"/>
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择类型">
            <el-option
              v-for="dict in dict.type.thesis_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="论文编号" prop="thesisNumber">
          <el-input v-model="form.thesisNumber" placeholder="请输入论文编号"/>
        </el-form-item>
        <el-form-item label="导师类别" prop="tutorType">
          <el-select v-model="form.tutorType" placeholder="请选择导师类别">
            <el-option
              v-for="dict in dict.type.author_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="作者" prop="userId">
          <el-select v-model="form.userId" filterable placeholder="请选择">
            <el-option
              v-for="item in userList"
              :key="item.userId"
              :label="item.userName"
              :value="item.userId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="发布时间" prop="publishTime">
          <el-date-picker clearable
                          v-model="form.publishTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择发布时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="发表级别" prop="publishLevel">
          <el-select v-model="form.publishLevel" placeholder="请选择发表级别">
            <el-option
              v-for="dict in dict.type.thesis_level"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="依托项目" prop="relyProject">
          <el-select v-model="form.relyProject" multiple filterable placeholder="请选择">
            <el-option
              v-for="item in projectInfoList"
              :key="item.id"
              :label="item.name"
              :value="item.name"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="论文文件" prop="file">
          <file-upload v-model="form.file"/>
        </el-form-item>
        <el-form-item label="页码" prop="pageNumber">
          <el-input v-model="form.pageNumber" placeholder="请输入页码"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listThesisInfo, getThesisInfo, delThesisInfo, addThesisInfo, updateThesisInfo } from '@/api/manage/thesisInfo'
import { listUserAll } from '@/api/system/user'
import { listProjectInfoAll } from '@/api/manage/projectInfo'

export default {
  name: 'ThesisInfo',
  dicts: ['author_type', 'thesis_type', 'thesis_level'],
  data() {
    return {
      baseUrl: process.env.VUE_APP_BASE_API,
      projectInfoList: [],
      userList: [],
      userInfo: {},
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 论文信息表格数据
      thesisInfoList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangePublishTime: [],
      // 备注时间范围
      daterangeCreateTime: [],
      // 备注时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        journal: null,
        title: null,
        type: null,
        thesisNumber: null,
        tutorType: null,
        userId: null,
        publishTime: null,
        publishLevel: null,
        relyProject: null,
        pageNumber: null,
        createBy: null,
        createTime: null,
        updateTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        journal: [
          { required: true, message: '期刊不能为空', trigger: 'blur' }
        ],
        title: [
          { required: true, message: '题目不能为空', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '类型不能为空', trigger: 'change' }
        ],
        thesisNumber: [
          { required: true, message: '论文编号不能为空', trigger: 'blur' }
        ],
        tutorType: [
          { required: true, message: '导师类别不能为空', trigger: 'change' }
        ],
        userId: [
          { required: true, message: '作者不能为空', trigger: 'blur' }
        ],
        publishTime: [
          { required: true, message: '发布时间不能为空', trigger: 'blur' }
        ],
        publishLevel: [
          { required: true, message: '发表级别不能为空', trigger: 'change' }
        ],
        pageNumber: [
          { required: true, message: '页码不能为空', trigger: 'blur' }
        ],
        createBy: [
          { required: true, message: '创建人不能为空', trigger: 'blur' }
        ],
        createTime: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
    this.getUserList()
    this.getProjectInfoList()
  },
  methods: {
    getProjectInfoList() {
      listProjectInfoAll().then(res => {
        this.projectInfoList = res.rows
      })
    },
    getUserList() {
      listUserAll(this.userInfo).then(res => {
        this.userList = res.rows
      })
    },
    /** 查询论文信息列表 */
    getList() {
      this.loading = true
      this.queryParams.params = {}
      if (null != this.daterangePublishTime && '' != this.daterangePublishTime) {
        this.queryParams.params['beginPublishTime'] = this.daterangePublishTime[0]
        this.queryParams.params['endPublishTime'] = this.daterangePublishTime[1]
      }
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params['beginCreateTime'] = this.daterangeCreateTime[0]
        this.queryParams.params['endCreateTime'] = this.daterangeCreateTime[1]
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params['beginUpdateTime'] = this.daterangeUpdateTime[0]
        this.queryParams.params['endUpdateTime'] = this.daterangeUpdateTime[1]
      }
      listThesisInfo(this.queryParams).then(response => {
        this.thesisInfoList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        journal: null,
        title: null,
        type: null,
        thesisNumber: null,
        tutorType: null,
        userId: null,
        publishTime: null,
        publishLevel: null,
        relyProject: null,
        file: null,
        pageNumber: null,
        remark: null,
        createBy: null,
        createTime: null,
        updateTime: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangePublishTime = []
      this.daterangeCreateTime = []
      this.daterangeUpdateTime = []
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加论文信息'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getThesisInfo(id).then(response => {
        this.form = response.data
        if (this.form.relyProject != null) {
          this.form.relyProject = this.form.relyProject.split(',')
        }
        this.open = true
        this.title = '修改论文信息'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.relyProject != null) {
            this.form.relyProject = this.form.relyProject.join(',')
          }
          if (this.form.id != null) {
            updateThesisInfo(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addThesisInfo(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除论文信息编号为"' + ids + '"的数据项？').then(function() {
        return delThesisInfo(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/thesisInfo/export', {
        ...this.queryParams
      }, `thesisInfo_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
