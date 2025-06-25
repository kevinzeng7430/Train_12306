<template>
  <div>
    <h1>乘车人管理</h1>
    <p>
      <a-space>
        <a-button type="primary" @click="handleQuery()">刷新</a-button>
        <a-button type="primary" @click="onAdd">新增</a-button>
      </a-space>
    </p>
    <a-table :data-source="passengers" :columns="columns" :pagination="pagination" @change="handleTableChange"
              :loading="loading">
      <template #bodyCell="{ column, record }">
        <template v-if="column.dataIndex === 'operation'">
          <a-space>
            <a @click="onEdit(record)">编辑</a>
            <a-popconfirm
              title="删除后不可恢复,是否确认删除？"
              @confirm="onDelete(record)"
              ok-text="确认" cancel-text="取消">
              <a style="color: red">删除</a>
            </a-popconfirm>
          </a-space>
        </template>
        <template v-if="column.dataIndex === 'type'">
          <span v-for="item in PASSENGER_TYPE_ARRAY" :key="item.key">
            <span v-if="item.key === record.type">{{ item.value }}
            </span>
          </span>
        </template>
      </template>
    </a-table>
    <a-modal v-model:visible="visible" title="新增乘车人" @ok="handleOk"
          ok-text="确定" cancel-text="取消">
      <a-form :model="passenger" :label-col="{span: 4}" :wrapper-col="{span: 20}">
          <a-form-item label="姓名">
            <a-input v-model:value="passenger.name" placeholder="请输入姓名"/>
          </a-form-item>
          <a-form-item label="身份证号">
            <a-input v-model:value="passenger.idCard" placeholder="请输入身份证号"/>
          </a-form-item>
        <a-form-item label="类型">
          <a-select v-model:value="passenger.type" placeholder="请选择类型">
            <a-select-option v-for="item in PASSENGER_TYPE_ARRAY" :key="item.value" :value="item.value">
              {{ item.value }}
            </a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script >
import {defineComponent, ref, onMounted} from 'vue';
import axios from "axios";
import {notification} from "ant-design-vue";
export default defineComponent({
  setup() {
    const visible = ref(false);
    let passenger = ref({
      id:undefined,
      memberId: undefined,
      name: undefined,
      idCard: undefined,
      type: undefined, // 1:成人, 2:儿童, 3:学生
      createTime: undefined,
      updateTime: undefined,
    });
    let loading = ref(false);
    const passengers = ref([]);

    const PASSENGER_TYPE_ARRAY = window.PASSENGER_TYPE_ARRAY;

    const onAdd = () => {
      passenger.value = {};
      visible.value = true;
    };
    const columns = [
      {
        title: '姓名',
        dataIndex: 'name',
        key: 'name',
      },
      {
        title: '身份证号',
        dataIndex: 'idCard',
        key: 'idCard',
      },
      {
        title: '类型',
        dataIndex: 'type',
        key: 'type',
      },
      {
        title: '操作',
        dataIndex: 'operation',
        key: 'operation',
      }
    ];
    const handleOk = e => {
      console.log(e);
      axios.post("member/passenger/save", passenger.value).then(response => {
        console.log(response);
        let data = response.data;
        if (data.success) {
          notification.success({message:"操作成功", description: "乘车人信息已保存"});
          visible.value = false;
          handleQuery({page: pagination.value.current, size: pagination.value.pageSize}); // 刷新列表
          // visible.value = false; // 关闭模态框
          // passenger.id = undefined; // 清空乘车人信息
          // passenger.memberId = undefined;
          // passenger.name = undefined;
          // passenger.idCard = undefined;
          // passenger.type = undefined;
          // passenger.createTime = undefined;
          // passenger.updateTime = undefined;
          // 可以在这里添加成功提示或刷新列表等操作
        } else {
          // 处理错误情况
          notification.error({message:"请求失败", description: data.message});
          console.error(data.message);
        }
      }).catch(error => {
        console.error("请求失败", error);
      });
    };

    const handleQuery = (param) =>{
      if(!param){
        param = {
          page: 1,
          size: pagination.value.pageSize,
        }
      }
      loading.value = true;
      axios.get("member/passenger/queryList",{
        params:{
          pageNum: param.page,
          pageSize: param.size,
        }
      }).then((response)=>{
        loading.value = false;
        let data = response.data;
        if(data.success){
          passengers.value = data.content.list;
          //让点击按钮也跟随
          pagination.value.current = param.page;
          pagination.value.total = data.content.total;
        }else {
          notification.error({message:"请求失败", description: data.message});
          console.error(data.message);
        }
      });
    };
    //分页的三个属性是固定的
    const pagination = ref({
      total: 0, // 总条数
      current: 1, // 当前页码
      pageSize: 2, // 每页条数
    });

    const handleTableChange = (pagination) => {
      // 处理分页、过滤和排序
      console.log(pagination);
      handleQuery({page: pagination.current, size: pagination.pageSize});
    };

    const onEdit = (record) => {
      passenger.value = {...record}; // 复制记录到乘车人对象
      visible.value = true; // 打开模态框
    };

    const onDelete = (record) => {
      console.log("删除记录", record);
      axios.delete("member/passenger/delete/"+ record.id).then(response => {
        let data = response.data;
        if (data.success) {
          notification.success({message:"操作成功", description: "乘车人信息已删除"});
          handleQuery({page: pagination.value.current, size: pagination.value.pageSize}); // 刷新列表
        } else {
          notification.error({message:"请求失败", description: data.message});
          console.error(data.message);
        }
      }).catch(error => {
        console.error("请求失败", error);
      });
    };

    //通过钩子函数（生命周期管理）等页面渲染完毕再执行查询
    onMounted(()=>{
      handleQuery({page: 1, size: pagination.value.pageSize});
    });
    return {
      passenger,
      visible,
      onAdd,
      handleOk,
      passengers,
      columns,
      pagination,
      handleTableChange,
      handleQuery,
      loading,
      onEdit,
      onDelete,
      PASSENGER_TYPE_ARRAY
    };
  },
});
</script>

<style>

</style>