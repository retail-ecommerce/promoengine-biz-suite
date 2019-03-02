

import React, { Component } from 'react'
import FontAwesome from 'react-fontawesome';
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from 'components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'
import {
  ChartCard, yuan, MiniArea, MiniBar, MiniProgress, Field, Bar, Pie, TimelineChart,
} from '../../components/Charts'
import Trend from '../../components/Trend'
import NumberInfo from '../../components/NumberInfo'
import { getTimeDistance } from '../../utils/utils'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './PromotionOffer.dashboard.less'
import DescriptionList from '../../components/DescriptionList';
import ImagePreview from '../../components/ImagePreview';
import GlobalComponents from '../../custcomponents';
import DashboardTool from '../../common/Dashboard.tool'
import appLocaleName from '../../common/Locale.tool'

const {aggregateDataset,calcKey, defaultHideCloseTrans,
  defaultImageListOf,defaultSettingListOf,defaultBuildTransferModal,
  defaultExecuteTrans,defaultHandleTransferSearch,defaultShowTransferModel,
  defaultRenderExtraHeader,
  defaultSubListsOf,
  defaultRenderExtraFooter,renderForTimeLine,renderForNumbers
}= DashboardTool



const { Description } = DescriptionList;
const { TabPane } = Tabs
const { RangePicker } = DatePicker
const { Option } = Select


const imageList =(promotionOffer)=>{return [
	 ]}

const internalImageListOf = (promotionOffer) =>defaultImageListOf(promotionOffer,imageList)

const optionList =(promotionOffer)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalSettingListOf = (promotionOffer) =>defaultSettingListOf(promotionOffer, optionList)
const internalLargeTextOf = (promotionOffer) =>{

	return null
	

}


const internalRenderExtraHeader = defaultRenderExtraHeader

const internalRenderExtraFooter = defaultRenderExtraFooter
const internalSubListsOf = defaultSubListsOf


const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <FontAwesome name="arrow-left"  /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName}</div>)

}


const internalSummaryOf = (promotionOffer,targetComponent) =>{
	
	
	const {PromotionOfferService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="Id">{promotionOffer.id}</Description> 
<Description term="Name">{promotionOffer.name}</Description> 
<Description term="Promotion Level">{promotionOffer.promotionLevel==null?appLocaleName(userContext,"NotAssigned"):promotionOffer.promotionLevel.displayName}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"Promotion Level","promotionLevel",PromotionOfferService.requestCandidatePromotionLevel,
	      PromotionOfferService.transferToAnotherPromotionLevel,"anotherPromotionLevelId",promotionOffer.promotionLevel?promotionOffer.promotionLevel.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="Parameter">{promotionOffer.parameter}</Description> 
<Description term="Promotion">{promotionOffer.promotion==null?appLocaleName(userContext,"NotAssigned"):promotionOffer.promotion.displayName}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"Promotion","promotion",PromotionOfferService.requestCandidatePromotion,
	      PromotionOfferService.transferToAnotherPromotion,"anotherPromotionId",promotionOffer.promotion?promotionOffer.promotion.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
	
        {buildTransferModal(promotionOffer,targetComponent)}
      </DescriptionList>
	)

}


class PromotionOfferDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'promotionOffer'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName,  } = this.props.promotionOffer
    if(!this.props.promotionOffer.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"Promotion Offer",cardsFor: "promotionOffer",
    	cardsSource: this.props.promotionOffer,returnURL,displayName,
  		subItems: [
    
      	],
  	};
    
    const renderExtraHeader = this.props.renderExtraHeader || internalRenderExtraHeader
    const settingListOf = this.props.settingListOf || internalSettingListOf
    const imageListOf = this.props.imageListOf || internalImageListOf
    const subListsOf = this.props.subListsOf || internalSubListsOf
    const largeTextOf = this.props.largeTextOf ||internalLargeTextOf
    const summaryOf = this.props.summaryOf || internalSummaryOf
    const renderTitle = this.props.renderTitle || internalRenderTitle
    const renderExtraFooter = this.props.renderExtraFooter || internalRenderExtraFooter
    return (

      <PageHeaderLayout
        title={renderTitle(cardsData,this)}
        content={summaryOf(cardsData.cardsSource,this)}
        wrapperClassName={styles.advancedForm}
      >
      {renderExtraHeader(cardsData.cardsSource)}
        <div>
        {settingListOf(cardsData.cardsSource)}
        {imageListOf(cardsData.cardsSource)}
        {subListsOf(cardsData)} 
        {largeTextOf(cardsData.cardsSource)}
          
        </div>
      </PageHeaderLayout>
    )
  }
}

export default connect(state => ({
  promotionOffer: state._promotionOffer,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(PromotionOfferDashboard))

