import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}actionTypeManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}actionTypeManager/loadActionType/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}actionTypeManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}actionTypeManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addTargetAction = (targetObjectId, parameters) => {
  const url = `${PREFIX}actionTypeManager/addTargetAction/actionTypeId/name/parameter/promotionId/tokensExpr/`
  const actionTypeId = targetObjectId
  const requestParameters = { ...parameters, actionTypeId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateTargetAction = (targetObjectId, parameters) => {
  const url = `${PREFIX}actionTypeManager/updateTargetActionProperties/actionTypeId/id/name/parameter/tokensExpr/`
  const actionTypeId = targetObjectId
  const requestParameters = { ...parameters, actionTypeId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeTargetActionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}actionTypeManager/removeTargetActionList/actionTypeId/targetActionIds/tokensExpr/`
  const requestParameters = { ...parameters, actionTypeId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}


const ActionTypeService = { view,
  load,
  addTargetAction,
  updateTargetAction,
  removeTargetActionList,
  requestCandidatePlatform,
  transferToAnotherPlatform }
export default ActionTypeService

