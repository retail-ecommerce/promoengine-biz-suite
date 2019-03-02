import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}targetActionManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}targetActionManager/loadTargetAction/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateAction = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}targetActionManager/requestCandidateAction/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherAction = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}targetActionManager/transferToAnotherAction/id/anotherActionId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidatePromotion = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}targetActionManager/requestCandidatePromotion/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPromotion = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}targetActionManager/transferToAnotherPromotion/id/anotherPromotionId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}






const TargetActionService = { view,
  load,
  requestCandidateAction,
  requestCandidatePromotion,
  transferToAnotherAction,
  transferToAnotherPromotion }
export default TargetActionService

