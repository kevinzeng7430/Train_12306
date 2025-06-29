<template>
  <p>
    <a-space>
      <a-button type="primary" @click="handleQuery()">刷新</a-button>
      <a-button type="primary" @click="onAdd">新增</a-button>
    </a-space>
  </p>
  <a-table :dataSource="stations"
           :columns="columns"
           :pagination="pagination"
           @change="handleTableChange"
           :loading="loading">
    <template #bodyCell="{ column, record }">
      <template v-if="column.dataIndex === 'operation'">
        <a-space>
          <a-popconfirm
              title="删除后不可恢复，确认删除?"
              @confirm="onDelete(record)"
              ok-text="确认" cancel-text="取消">
            <a style="color: red">删除</a>
          </a-popconfirm>
          <a @click="onEdit(record)">编辑</a>
        </a-space>
      </template>
    </template>
  </a-table>
  <a-modal v-model:visible="visible" title="车站" @ok="handleOK"
           ok-text="确认" cancel-text="取消">
    <a-form :model="station" :label-col="{span: 4}" :wrapper-col="{ span: 20 }">
      <a-form-item label="站名">
        <a-input v-model:value="station.name"/>
      </a-form-item>
      <a-form-item label="站名拼音">
        <a-input v-model:value="station.namePinyin" disabled/>
      </a-form-item>
      <a-form-item label="拼音首字母">
        <a-input v-model:value="station.namePy" disabled/>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
import {defineComponent, onMounted, ref, watch} from 'vue';
import axios from "axios";
import {notification} from "ant-design-vue";
import {pinyin} from "pinyin-pro";

export default defineComponent({
  name: "station-view",
  setup() {
    // 这里可以添加一些初始化逻辑
    const visible = ref(false); // 控制模态框的显示和隐藏
    let station = ref({
      id: undefined,
      name: undefined,
      namePinyin: undefined,
      namePy: undefined,
      createTime: undefined,
      updateTime: undefined,
    });
    const stations = ref([]); // 车站列表,后端数据库传过来的list
    //分页三个属性
    const pagination = ref({
      total: 0, // 总记录数
      current: 1, // 当前页码
      pageSize: 2, // 每页记录数
    });
    let loading = ref(false); // 控制加载状态
    const columns = [
      {
        title: '站名',
        dataIndex: 'name',
        key: 'name',
      },
      {
        title: '站名拼音',
        dataIndex: 'namePinyin',
        key: 'namePinyin',
      },
      {
        title: '拼音首字母',
        dataIndex: 'namePy',
        key: 'namePy',
      },
      {
        title: '操作',
        dataIndex: 'operation',
        key: 'operation',
      },
    ];
    //
    watch(() => station.value.name, ()=>{
      if(Tool.isNotEmpty(station.value.name)){
        station.value.namePinyin = pinyin(station.value.name, {toneType:"none"}).replaceAll(" ", "");
        station.value.namePy = pinyin(station.value.name, {pattern:'first', toneType: "none"}).replaceAll(" ","");
      }else{
        station.value.namePinyin = "";
        station.value.namePy = "";
      }
    }, {immediate:true});

    //增加车站信息功能
    const onAdd = () => {
      //先将模态框清空
      station.value = {};
      visible.value = true;
    };
    const handleOK = () => {
      axios.post("business/admin/station/save", station.value)
          .then(response => {
            let data = response.data;
            if (data.success) {
              notification.success({message:"保存成功", description: data.message});
              visible.value = false; // 关闭模态框
              handleQuery({page: pagination.value.current, size: pagination.value.pageSize}); // 刷新列表
            } else {
              notification.error({message:"保存失败", description:data.message});
              console.error(data.message);
            }
          });
    };

    const handleQuery = (param) => {
      if (!param) {
        param = {
          page: 1,
          size: pagination.value.pageSize,
        };
      }
      loading.value = true;

      axios.get("business/admin/station/query-list", {
        params: {
          pageNum: param.page,
          pageSize: param.size,
        }
      }).then(response => {
        let data = response.data;
        loading.value = false;
        if (data.success) {
          notification.success({description: "查询成功"})
          stations.value = data.content.list
          pagination.value.current = param.page
          pagination.value.total = data.content.total
        } else {
          notification.error({description: "查询失败"})
          console.error(data.message)
        }
      })
    };

    //编辑
    const onEdit = (record) => {
      station.value = window.Tool.copy(record)
      visible.value = true
    }

    //点击图标跟随
    const handleTableChange = (pagination) => {
      handleQuery({
        page: pagination.current,
        size: pagination.pageSize
      })
    }
    //这是因为你在 JS 中访问 ref 的值，必须用 .value。而在模板中绑定 :pagination="pagination" 时则不需要。


    //删除功能
    const onDelete = (record) => {
      console.log("删除记录", record);
      axios.delete("business/admin/station/delete/" + record.id)
          .then(response => {
            let data = response.data;
            if (data.success) {
              notification.success({description: "删除成功"})
              handleQuery({
                page: pagination.value.current,
                size: pagination.value.pageSize
              })
            } else {
              notification.error({description:"删除失败"})
            }
          }).catch( error => {
          console.error(error.message)
      })
    }

    onMounted(() => {
      handleQuery({
        page: 1,
        size: pagination.value.pageSize
      })
    });
    return {
      visible,
      pagination,
      loading,
      station,
      stations,
      columns,
      onAdd,
      handleOK,
      handleQuery,
      onEdit,
      handleTableChange,
      onDelete,
      onMounted,
    };
  },

});

</script>

<style>

</style>