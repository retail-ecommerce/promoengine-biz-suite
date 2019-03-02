import React from 'react'
import PropTypes from 'prop-types'
import {
  Layout,
  Menu,
  Icon,
  Avatar,
  Dropdown,
  Tag,
  message,
  Spin,
  Breadcrumb,
  AutoComplete,
  Input,Button
} from 'antd'
import DocumentTitle from 'react-document-title'
import { connect } from 'dva'
import { Link, Route, Redirect, Switch } from 'dva/router'
import moment from 'moment'
import groupBy from 'lodash/groupBy'
import { ContainerQuery } from 'react-container-query'
import classNames from 'classnames'
import styles from './Platform.app.less'
import {sessionObject} from '../../utils/utils'

import HeaderSearch from '../../components/HeaderSearch';
import NoticeIcon from '../../components/NoticeIcon';
import GlobalFooter from '../../components/GlobalFooter';


import GlobalComponents from '../../custcomponents';

import PermissionSettingService from '../../permission/PermissionSetting.service'
import appLocaleName from '../../common/Locale.tool'

const  {  filterForMenuPermission } = PermissionSettingService

const isMenuItemForDisplay = (item, targetObject, targetComponent) => {
  return true
}

const filteredMenuItems = (targetObject, targetComponent) => {
    const menuData = sessionObject('menuData')
    const isMenuItemForDisplayFunc = targetComponent.props.isMenuItemForDisplayFunc||isMenuItemForDisplay
    return menuData.subItems.filter(item=>filterForMenuPermission(item,targetObject,targetComponent)).filter(item=>isMenuItemForDisplayFunc(item,targetObject,targetComponent))
}



const { Header, Sider, Content } = Layout
const { SubMenu } = Menu

const query = {
  'screen-xs': {
    maxWidth: 575,
  },
  'screen-sm': {
    minWidth: 576,
    maxWidth: 767,
  },
  'screen-md': {
    minWidth: 768,
    maxWidth: 991,
  },
  'screen-lg': {
    minWidth: 992,
    maxWidth: 1199,
  },
  'screen-xl': {
    minWidth: 1200,
  },
}




class PlatformBizApp extends React.PureComponent {
  constructor(props) {
    super(props)
     this.state = {
      openKeys: this.getDefaultCollapsedSubMenus(props),
    }
  }

  componentDidMount() {}
  componentWillUnmount() {
    clearTimeout(this.resizeTimeout)
  }
  onCollapse = (collapsed) => {
    this.props.dispatch({
      type: 'global/changeLayoutCollapsed',
      payload: collapsed,
    })
  }

  getDefaultCollapsedSubMenus = (props) => {
    const currentMenuSelectedKeys = [...this.getCurrentMenuSelectedKeys(props)]
    currentMenuSelectedKeys.splice(-1, 1)
    if (currentMenuSelectedKeys.length === 0) {
      return ['/platform/']
    }
    return currentMenuSelectedKeys
  }
  getCurrentMenuSelectedKeys = (props) => {
    const { location: { pathname } } = props || this.props
    const keys = pathname.split('/').slice(1)
    if (keys.length === 1 && keys[0] === '') {
      return [this.menus[0].key]
    }
    return keys
  }
  
  getNavMenuItems = (targetObject) => {
  

    const menuData = sessionObject('menuData')
    const targetApp = sessionObject('targetApp')
	const {objectId}=targetApp;
  	const userContext = null
    return (
      
		  <Menu
             theme="dark"
             mode="inline"
            
             
             onOpenChange={this.handleOpenChange}
            
             defaultOpenKeys={['firstOne']}
             style={{ margin: '16px 0', width: '100%' }}
           >
           

             <Menu.Item key="dashboard">
               <Link to={`/platform/${this.props.platform.id}/dashboard`}><Icon type="dashboard" /><span>{appLocaleName(userContext,"Dashboard")}</span></Link>
             </Menu.Item>
             
		 <Menu.Item key="homepage">
               <Link to={"/home"}><Icon type="home" /><span>{appLocaleName(userContext,"Home")}</span></Link>
             </Menu.Item>
             
             
         {filteredMenuItems(targetObject,this).map((item)=>(<Menu.Item key={item.name}>
          <Link to={`/${menuData.menuFor}/${objectId}/list/${item.name}/${item.displayName}${appLocaleName(userContext,"List")}`}>
          <Icon type="bars" /><span>{item.displayName}</span>
          </Link>
        </Menu.Item>))}
       
       <Menu.Item key="preference">
               <Link to={`/platform/${this.props.platform.id}/preference`}><Icon type="setting" /><span>{appLocaleName(userContext,"Preference")}</span></Link>
             </Menu.Item>
      
           </Menu>
    )
  }
  



  getApplyConditionSearch = () => {
    const {ApplyConditionSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: "Apply Condition",
      role: "applyCondition",
      data: state._platform.applyConditionList,
      metaInfo: state._platform.applyConditionListMetaInfo,
      count: state._platform.applyConditionCount,
      currentPage: state._platform.applyConditionCurrentPageNumber,
      searchFormParameters: state._platform.applyConditionSearchFormParameters,
      searchParameters: {...state._platform.searchParameters},
      expandForm: state._platform.expandForm,
      loading: state._platform.loading,
      partialList: state._platform.partialList,
      owner: { type: '_platform', id: state._platform.id, 
      referenceName: 'platform', 
      listName: 'applyConditionList', ref:state._platform, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ApplyConditionSearch)
  }
  getApplyConditionCreateForm = () => {
   	const {ApplyConditionCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "applyCondition",
      data: state._platform.applyConditionList,
      metaInfo: state._platform.applyConditionListMetaInfo,
      count: state._platform.applyConditionCount,
      currentPage: state._platform.applyConditionCurrentPageNumber,
      searchFormParameters: state._platform.applyConditionSearchFormParameters,
      loading: state._platform.loading,
      owner: { type: '_platform', id: state._platform.id, referenceName: 'platform', listName: 'applyConditionList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(ApplyConditionCreateForm)
  }
  
  getApplyConditionUpdateForm = () => {
    const userContext = null
  	const {ApplyConditionUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._platform.selectedRows,
      role: "applyCondition",
      currentUpdateIndex: state._platform.currentUpdateIndex,
      owner: { type: '_platform', id: state._platform.id, listName: 'applyConditionList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ApplyConditionUpdateForm)
  }

  getActionTypeSearch = () => {
    const {ActionTypeSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: "Action Type",
      role: "actionType",
      data: state._platform.actionTypeList,
      metaInfo: state._platform.actionTypeListMetaInfo,
      count: state._platform.actionTypeCount,
      currentPage: state._platform.actionTypeCurrentPageNumber,
      searchFormParameters: state._platform.actionTypeSearchFormParameters,
      searchParameters: {...state._platform.searchParameters},
      expandForm: state._platform.expandForm,
      loading: state._platform.loading,
      partialList: state._platform.partialList,
      owner: { type: '_platform', id: state._platform.id, 
      referenceName: 'platform', 
      listName: 'actionTypeList', ref:state._platform, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ActionTypeSearch)
  }
  getActionTypeCreateForm = () => {
   	const {ActionTypeCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "actionType",
      data: state._platform.actionTypeList,
      metaInfo: state._platform.actionTypeListMetaInfo,
      count: state._platform.actionTypeCount,
      currentPage: state._platform.actionTypeCurrentPageNumber,
      searchFormParameters: state._platform.actionTypeSearchFormParameters,
      loading: state._platform.loading,
      owner: { type: '_platform', id: state._platform.id, referenceName: 'platform', listName: 'actionTypeList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(ActionTypeCreateForm)
  }
  
  getActionTypeUpdateForm = () => {
    const userContext = null
  	const {ActionTypeUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._platform.selectedRows,
      role: "actionType",
      currentUpdateIndex: state._platform.currentUpdateIndex,
      owner: { type: '_platform', id: state._platform.id, listName: 'actionTypeList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ActionTypeUpdateForm)
  }

  getPromotionLevelSearch = () => {
    const {PromotionLevelSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: "Promotion Level",
      role: "promotionLevel",
      data: state._platform.promotionLevelList,
      metaInfo: state._platform.promotionLevelListMetaInfo,
      count: state._platform.promotionLevelCount,
      currentPage: state._platform.promotionLevelCurrentPageNumber,
      searchFormParameters: state._platform.promotionLevelSearchFormParameters,
      searchParameters: {...state._platform.searchParameters},
      expandForm: state._platform.expandForm,
      loading: state._platform.loading,
      partialList: state._platform.partialList,
      owner: { type: '_platform', id: state._platform.id, 
      referenceName: 'platform', 
      listName: 'promotionLevelList', ref:state._platform, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(PromotionLevelSearch)
  }
  getPromotionLevelCreateForm = () => {
   	const {PromotionLevelCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "promotionLevel",
      data: state._platform.promotionLevelList,
      metaInfo: state._platform.promotionLevelListMetaInfo,
      count: state._platform.promotionLevelCount,
      currentPage: state._platform.promotionLevelCurrentPageNumber,
      searchFormParameters: state._platform.promotionLevelSearchFormParameters,
      loading: state._platform.loading,
      owner: { type: '_platform', id: state._platform.id, referenceName: 'platform', listName: 'promotionLevelList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(PromotionLevelCreateForm)
  }
  
  getPromotionLevelUpdateForm = () => {
    const userContext = null
  	const {PromotionLevelUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._platform.selectedRows,
      role: "promotionLevel",
      currentUpdateIndex: state._platform.currentUpdateIndex,
      owner: { type: '_platform', id: state._platform.id, listName: 'promotionLevelList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(PromotionLevelUpdateForm)
  }

  getPromotionSearch = () => {
    const {PromotionSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: "Promotion",
      role: "promotion",
      data: state._platform.promotionList,
      metaInfo: state._platform.promotionListMetaInfo,
      count: state._platform.promotionCount,
      currentPage: state._platform.promotionCurrentPageNumber,
      searchFormParameters: state._platform.promotionSearchFormParameters,
      searchParameters: {...state._platform.searchParameters},
      expandForm: state._platform.expandForm,
      loading: state._platform.loading,
      partialList: state._platform.partialList,
      owner: { type: '_platform', id: state._platform.id, 
      referenceName: 'platform', 
      listName: 'promotionList', ref:state._platform, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(PromotionSearch)
  }
  getPromotionCreateForm = () => {
   	const {PromotionCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "promotion",
      data: state._platform.promotionList,
      metaInfo: state._platform.promotionListMetaInfo,
      count: state._platform.promotionCount,
      currentPage: state._platform.promotionCurrentPageNumber,
      searchFormParameters: state._platform.promotionSearchFormParameters,
      loading: state._platform.loading,
      owner: { type: '_platform', id: state._platform.id, referenceName: 'platform', listName: 'promotionList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(PromotionCreateForm)
  }
  
  getPromotionUpdateForm = () => {
    const userContext = null
  	const {PromotionUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._platform.selectedRows,
      role: "promotion",
      currentUpdateIndex: state._platform.currentUpdateIndex,
      owner: { type: '_platform', id: state._platform.id, listName: 'promotionList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(PromotionUpdateForm)
  }


  
  buildRouters = () =>{
  	const {PlatformDashboard} = GlobalComponents
  	const {PlatformPreference} = GlobalComponents
  	
  	
  	const routers=[
  	{path:"/platform/:id/dashboard", component: PlatformDashboard},
  	{path:"/platform/:id/preference", component: PlatformPreference},
  	
  	
  	
  	{path:"/platform/:id/list/applyConditionList", component: this.getApplyConditionSearch()},
  	{path:"/platform/:id/list/applyConditionCreateForm", component: this.getApplyConditionCreateForm()},
  	{path:"/platform/:id/list/applyConditionUpdateForm", component: this.getApplyConditionUpdateForm()},
   	
  	{path:"/platform/:id/list/actionTypeList", component: this.getActionTypeSearch()},
  	{path:"/platform/:id/list/actionTypeCreateForm", component: this.getActionTypeCreateForm()},
  	{path:"/platform/:id/list/actionTypeUpdateForm", component: this.getActionTypeUpdateForm()},
   	
  	{path:"/platform/:id/list/promotionLevelList", component: this.getPromotionLevelSearch()},
  	{path:"/platform/:id/list/promotionLevelCreateForm", component: this.getPromotionLevelCreateForm()},
  	{path:"/platform/:id/list/promotionLevelUpdateForm", component: this.getPromotionLevelUpdateForm()},
   	
  	{path:"/platform/:id/list/promotionList", component: this.getPromotionSearch()},
  	{path:"/platform/:id/list/promotionCreateForm", component: this.getPromotionCreateForm()},
  	{path:"/platform/:id/list/promotionUpdateForm", component: this.getPromotionUpdateForm()},
     	
  	
  	]
  	
  	const {extraRoutesFunc} = this.props;
	const extraRoutes = extraRoutesFunc?extraRoutesFunc():[]
    const finalRoutes = routers.concat(extraRoutes)
    
  	return (<Switch>
             {finalRoutes.map((item)=>(<Route key={item.path} path={item.path} component={item.component} />))}    
  	  	</Switch>)
  	
  
  }
 

  getPageTitle = () => {
    // const { location } = this.props
    // const { pathname } = location
    const title = 'Promotion Service'
    return title
  }
 
  handleOpenChange = (openKeys) => {
    const latestOpenKey = openKeys.find(key => this.state.openKeys.indexOf(key) === -1)
    this.setState({
      openKeys: latestOpenKey ? [latestOpenKey] : [],
    })
  }
   toggle = () => {
     const { collapsed } = this.props
     this.props.dispatch({
       type: 'global/changeLayoutCollapsed',
       payload: !collapsed,
     })
   }
    logout = () => {
   
    console.log("log out called")
    this.props.dispatch({ type: 'launcher/signOut' })
  }
   render() {
     // const { collapsed, fetchingNotices,loading } = this.props
     const { collapsed } = this.props
     const { breadcrumb }  = this.props
  
     const targetApp = sessionObject('targetApp')
     const currentBreadcrumb =sessionObject(targetApp.id)
     const userContext = null
     
     const menuProps = collapsed ? {} : {
       openKeys: this.state.openKeys,
     }
     const layout = (
     <Layout>
        <Header>
          
          <div className={styles.left}>
          <img
            src="./favicon.png"
            alt="logo"
            onClick={this.toggle}
            className={styles.logo}
          />
          {currentBreadcrumb.map((item)=>{
            return (<Link  key={item.link} to={`${item.link}`} className={styles.breadcrumbLink}> &gt;{item.name}</Link>)

          })}
         </div>
          <div className={styles.right}  >
          <Button type="primary"  icon="logout" onClick={()=>this.logout()}>
          {appLocaleName(userContext,"Exit")}</Button>
          </div>
          
        </Header>
       <Layout>
         <Sider
           trigger={null}
           collapsible
           collapsed={collapsed}
           breakpoint="md"
           onCollapse={()=>this.onCollapse(collapsed)}
           collapsedWidth={56}
           className={styles.sider}
         >

		 {this.getNavMenuItems(this.props.platform)}
		 
         </Sider>
         <Layout>
           <Content style={{ margin: '24px 24px 0', height: '100%' }}>
           
           {this.buildRouters()}
 
             
             
           </Content>
          </Layout>
        </Layout>
      </Layout>
     )
     return (
       <DocumentTitle title={this.getPageTitle()}>
         <ContainerQuery query={query}>
           {params => <div className={classNames(params)}>{layout}</div>}
         </ContainerQuery>
       </DocumentTitle>
     )
   }
}

export default connect(state => ({
  collapsed: state.global.collapsed,
  fetchingNotices: state.global.fetchingNotices,
  notices: state.global.notices,
  platform: state._platform,
  ...state,
}))(PlatformBizApp)



