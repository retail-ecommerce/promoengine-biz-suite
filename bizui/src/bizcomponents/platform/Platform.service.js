import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}platformManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}platformManager/loadPlatform/${targetObjectId}/${parametersExpr}/`,
  })
}







const addApplyCondition = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addApplyCondition/platformId/name/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateApplyCondition = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updateApplyConditionProperties/platformId/id/name/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeApplyConditionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removeApplyConditionList/platformId/applyConditionIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addActionType = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addActionType/platformId/name/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateActionType = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updateActionTypeProperties/platformId/id/name/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeActionTypeList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removeActionTypeList/platformId/actionTypeIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addPromotionLevel = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addPromotionLevel/platformId/name/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updatePromotionLevel = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updatePromotionLevelProperties/platformId/id/name/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removePromotionLevelList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removePromotionLevelList/platformId/promotionLevelIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addPromotion = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addPromotion/platformId/name/validAfter/expireTime/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updatePromotion = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updatePromotionProperties/platformId/id/name/validAfter/expireTime/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removePromotionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removePromotionList/platformId/promotionIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}


const PlatformService = { view,
  load,
  addApplyCondition,
  addActionType,
  addPromotionLevel,
  addPromotion,
  updateApplyCondition,
  updateActionType,
  updatePromotionLevel,
  updatePromotion,
  removeApplyConditionList,
  removeActionTypeList,
  removePromotionLevelList,
  removePromotionList }
export default PlatformService

